package com.prcymy.ymy.delegates;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.prcymy.ymy.ui.camera.CameraImage;
import com.prcymy.ymy.ui.camera.MallCamera;
import com.prcymy.ymy.ui.camera.RequestCodes;
import com.prcymy.ymy.utils.callback.CallbackManager;
import com.prcymy.ymy.utils.callback.CallbackType;
import com.prcymy.ymy.utils.callback.IGlobalCallback;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

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

        if (requestCode == RESULT_OK){
            switch (requestCode){
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImage.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);

                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null){
                        final  Uri pickCropPath = data.getData();
                        //从相册选择后存放裁剪后的图片
                        final String pickResult = MallCamera.createCropFile().getPath();
                        UCrop.of(pickCropPath, Uri.parse(pickResult))
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