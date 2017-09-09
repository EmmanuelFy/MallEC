package com.prcymy.ymy.delegates;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.prcymy.ymy.R;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.ui.camera.CameraImage;
import com.prcymy.ymy.ui.camera.MallCamera;
import com.prcymy.ymy.ui.camera.RequestCodes;
import com.prcymy.ymy.utils.callback.CallbackManager;
import com.prcymy.ymy.utils.callback.CallbackType;
import com.prcymy.ymy.utils.callback.IGlobalCallback;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.view.UCropView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.yalantis.ucrop.UCrop.EXTRA_OUTPUT_CROP_ASPECT_RATIO;

/**
 * Created by Administrator on 2017/7/29.
 */

@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {



    //不是直接点用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    public void startCamera(){
        MallCamera.start(this);
    }

    public void statCameraWithCheck(){
       PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);

    }


    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied(){
        Toast.makeText(_mActivity, "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever(){
        Toast.makeText(_mActivity, "永久拒绝", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request){
        showRationaleDialog(request);
    }


    public void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //初始化裁剪框
        UCrop.Options options = new UCrop.Options();
        //设置为圆形
        options.setCircleDimmedLayer(true);
        //去除横线
        options.setCropGridColumnCount(0);
        //去除竖线
        options.setCropGridRowCount(0);
        //设置Toolbar颜色
        options.setToolbarColor(Color.WHITE);
        //设置Toolbar文字颜色
        options.setToolbarWidgetColor(Color.BLACK);
        //设置状态栏颜色
        options.setStatusBarColor(Color.BLACK);
        //隐藏底部
        options.setHideBottomControls(true);
        //隐藏裁剪边框线
        options.setShowCropFrame(false);

        if (resultCode == RESULT_OK){
            switch (requestCode){
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImage.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withOptions(options)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);

                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null){
                        final  Uri pickCropPath = data.getData();
                        //从相册选择后存放裁剪后的图片
                        final String pickResult = MallCamera.createCropFile().getPath();
                        UCrop.of(pickCropPath, Uri.parse(pickResult))
                                .withOptions(options)
                                .withMaxResultSize(400,400)
                                .start(getContext(),this);
                    }

                    break;
                case RequestCodes.CROP_PHOTO:
                    final  Uri cropUri = UCrop.getOutput(data);
                    //拿到裁剪后的数据进行处理
                    final IGlobalCallback<Uri> callback = CallbackManager.getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback != null){
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(_mActivity, "裁剪出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}