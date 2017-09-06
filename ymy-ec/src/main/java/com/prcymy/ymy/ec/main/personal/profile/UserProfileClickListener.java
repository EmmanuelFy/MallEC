package com.prcymy.ymy.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.orhanobut.logger.Logger;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.main.TestDelegate;
import com.prcymy.ymy.ec.main.personal.list.ListBean;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.utils.Date.DateDialogUtil;
import com.prcymy.ymy.utils.callback.CallbackManager;
import com.prcymy.ymy.utils.callback.CallbackType;
import com.prcymy.ymy.utils.callback.IGlobalCallback;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final MallDelegate DELEGATE;
    private String[] mGender = new String[]{"男","女","保密"};

    public UserProfileClickListener(MallDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, final int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
                                Logger.d("ON_CROP",args);
                                ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE).load(args).into(avatar);

                                //头像上传至服务器,
                                RestClient.builder()
                                        .url("api/cart")
                                        .params("id","62")
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                //更新本地数据库
                                                Logger.d(response);
                                            }
                                        })

                                        .error(new IError() {
                                            @Override
                                            public void onError(int code, String msg) {
                                                Logger.d("onError",msg);
                                            }
                                        })

                                        .fail(new IFail() {
                                            @Override
                                            public void onFail() {
                                                Logger.d("onFail");
                                            }
                                        })
                                        .build()
                                        .post();
                            }
                        });




                //拍照或选择图片
                DELEGATE.statCameraWithCheck();

                break;
            case 2:
                //姓名
                final MallDelegate nameDelegate = bean.getmDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                //性别
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGender[which]);
                        dialogInterface.cancel();
                    }
                });
                break;
            case 4:

                //年龄日期
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListner(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });

                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGender,0,listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
