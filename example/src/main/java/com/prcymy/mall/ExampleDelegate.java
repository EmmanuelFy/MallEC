package com.prcymy.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;

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


    }
}
