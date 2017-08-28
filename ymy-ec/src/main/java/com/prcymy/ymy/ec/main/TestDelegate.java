package com.prcymy.ymy.ec.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
 * Created by Administrator on 2017/8/24.
 */

public class TestDelegate extends MallDelegate implements ISuccess,IError,IFail{

    @BindView(R2.id.btn_start)
    Button button = null;

    @OnClick(R2.id.btn_start)
    void onClick(){
        RestClient.builder()
                .url("api/lessons")
                .params("name","12345678900")
                .params("password","12456")
                .success(this)
                .error(this)
                .fail(this)
                .build()
                .post();



    }


    @Override
    public Object setLayout() {
        return R.layout.test_delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


    }

    @Override
    public void onFail() {
        Toast.makeText(_mActivity, "fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int code, String msg) {
        Toast.makeText(_mActivity, msg+"onError", Toast.LENGTH_SHORT).show();
        Log.i("-----------",code+"---"+msg.toString());
    }

    @Override
    public void onSuccess(String response) {
        Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
    }
}
