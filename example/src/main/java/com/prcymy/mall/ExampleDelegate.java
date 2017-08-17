package com.prcymy.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/7/29.
 */

public class ExampleDelegate extends MallDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                     Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getActivity(), "onError", Toast.LENGTH_SHORT).show();
                    }
                })
                .fail(new IFail() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getActivity(), "onFail", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();

    }
}
