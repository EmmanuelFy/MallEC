package com.prcymy.ymy.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RegisterPassWordDelegate extends MallDelegate {

    private String phone;

    @BindView(R2.id.et_passwarod)
    EditText et_password = null;

    @BindView(R2.id.et_passwarod_verify)
    EditText et_password_verify = null;

    @OnClick(R2.id.btn_sub)
    void onClickSub(){

        if (checkForm()){

            //加密密码
            String password = EncryptUtils.encryptMD5ToString(et_password.getText().toString());
            Logger.d(password);
            RestClient.builder()
                    .url("api/add_pass")
                    .params("mobile",phone)
                    .params("password",password)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Logger.json(response);
                            JSONObject jsonObject = JSON.parseObject(response);
                            int code = jsonObject.getInteger("code");
                            String desc = jsonObject.getString("Desc");

                            if (code == 200){
                                Toast.makeText(_mActivity, "验证通过", Toast.LENGTH_SHORT).show();
                            }

                            if (code == 400){
                                Toast.makeText(_mActivity, "400错误"+desc, Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .build()
                    .post();
        }else {
            Toast.makeText(_mActivity, "密码不一致", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_register_password;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        phone = getArguments().getString("phone");
    }

    private boolean checkForm(){
        final String password = et_password.getText().toString();
        final String password_verif = et_password_verify.getText().toString();

        boolean isPass = true;

        if (!password.equals(password_verif)){
           isPass = false;
        }

        return isPass;
    }
}
