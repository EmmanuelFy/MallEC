package com.prcymy.ymy.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.EncryptUtils;
import com.orhanobut.logger.Logger;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/3.
 */

public class SignInDelegate extends MallDelegate {

    @BindView(R2.id.edit_sign_in_name)
    TextInputEditText mName = null;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    //登录按钮
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            //登录操作

            //密码加密
            String p  = EncryptUtils.encryptMD5ToString(mPassword.getText().toString());

            Logger.d(p);

            RestClient.builder()
                    .url("api/show")
                    .params("mobile_phone",mName.getText().toString())
                    .params("password",p)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Logger.json(response);
                            JSONObject jsonObject = JSON.parseObject(response);
                            int code = jsonObject.getInteger("code");
                            String desc = jsonObject.getString("Desc");
                            if (code == 200){
                                Toast.makeText(_mActivity, desc, Toast.LENGTH_SHORT).show();
                            }

                            if (code == 400){
                                Toast.makeText(_mActivity, desc, Toast.LENGTH_SHORT).show();
                            }
                            //调用解析 传入回调
                           //SignHandler.onSignIn(response,mISignListener);
                        }
                    })
                    .fail(new IFail() {
                        @Override
                        public void onFail() {
                            Toast.makeText(_mActivity, "onFail", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();


        }
    }

    //去注册
    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignUp(){
        start(new SignUpDelegate());
    }

    //微信登录
    @OnClick(R2.id.icon_sign_in_wechat)
    void wechatSign() {

    }

    public boolean checkForm() {

        String name = mName.getText().toString();
        String password = mName.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("用户名不能为空");
        } else {
            mName.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码不正确");
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
