package com.prcymy.ymy.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;

/**
 * Created by Administrator on 2017/9/4.
 */

public class NameDelegate extends MallDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
