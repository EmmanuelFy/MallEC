package com.prcymy.ymy.ec.sign;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.prcymy.ymy.ui.wegit.CountDownTimerButton;
import com.prcymy.ymy.utils.Http;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/3.
 */

public class SignUpDelegate extends MallDelegate {

    @BindView(R2.id.et_sign_phone)
    EditText et_sign_phone = null;

    @BindView(R2.id.et_sign_code)
    EditText et_sign_code = null;

    @BindView(R2.id.btn_sign_up_code)
    AppCompatButton btn_sign_up_code = null;

    @BindView(R2.id.btn_sign_up)
    Button btn_start = null;

    @BindView(R2.id.tool_bar)
    Toolbar toolbar = null;

    private ISignListener mISignListener = null;
    private CountDownTime countDownTime;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;

        }
    }

    @OnClick(R2.id.btn_sign_up_code)
    void OnClickCode(){
        //初始化倒计时
        //发送验证码
        if (!et_sign_phone.getText().toString().isEmpty() && et_sign_phone.getText().length() == 11){

          RestClient.builder()
                  .url(Http.REGISTER_SEND_PHONE)
                  .params("mobile",et_sign_phone.getText().toString())
                  .success(new ISuccess() {
                      @Override
                      public void onSuccess(String response) {
                          Logger.json(response);
                          JSONObject jsonObject = JSON.parseObject(response);
                          int code = jsonObject.getInteger("code");
                          String des = jsonObject.getString("Desc");
                          Logger.d(code);
                          Logger.d(des);
                          if (code == 200){
                              new CountDownTime(1000,6000).start();
                              Toast.makeText(_mActivity, "发送成功", Toast.LENGTH_SHORT).show();
                          }

                          if (code == 400){
                              Toast.makeText(_mActivity,des , Toast.LENGTH_SHORT).show();
                          }
                      }
                  })
                  .build()
                  .post();
        }else {
            Toast.makeText(_mActivity, "手机号码有误", Toast.LENGTH_SHORT).show();
        }
    }

    //注册
    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){

            RestClient.builder()
                    .url(Http.APP_REGSITER)
                    .params("RandKey",et_sign_code.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            //调用解析 传入回调
                            JSONObject jsonObject = JSON.parseObject(response);
                            final int code = jsonObject.getInteger("code");
                            final String desc = jsonObject.getString("Desc");

                            if (code == 200){
                                //跳转设置密码页面
                                Bundle bundle = new Bundle();
                                bundle.putString("phone",et_sign_phone.getText().toString());
                                RegisterPassWordDelegate delegate = new RegisterPassWordDelegate();
                                delegate.setArguments(bundle);
                                start(delegate);
                            }
                            if (code == 400){
                                Toast.makeText(_mActivity, desc, Toast.LENGTH_SHORT).show();
                            }
                            Logger.json(response);
                            //SignHandler.onSignUp(response,mISignListener);

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

    //判断是否为空
    private boolean checkForm(){
        final String phone = et_sign_phone.getText().toString();
        final String code = et_sign_code.getText().toString();

        boolean isPass = true;

       if (phone.isEmpty() && phone.length() != 11){
           isPass = false;
       }

       if (code.isEmpty()){
           isPass = false;
       }
        return isPass;
    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


        btn_start.setClickable(false);
        btn_start.setBackgroundColor(Color.GRAY);

        et_sign_code.addTextChangedListener(textWatcher);

    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 6){
                btn_start.setClickable(true);
                btn_start.setBackgroundColor(Color.parseColor("#eb4343"));
            }else {
                btn_start.setClickable(false);
                btn_start.setBackgroundColor(Color.GRAY);
            }

        }
    };


    //内部类倒计时
    class CountDownTime extends CountDownTimer {

        //构造函数  第一个参数代表总的计时时长  第二个参数代表计时间隔  单位都是毫秒
        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {  //每计时一次回调一次该方法
            btn_sign_up_code.setText(l/1000+"s");
            btn_sign_up_code.setClickable(false);
        }

        @Override
        public void onFinish() { //计时结束回调该方法
            btn_sign_up_code.setClickable(true);
            btn_sign_up_code.setText("获取验证码");
        }
    }
}
