package com.prcymy.ymy.ec.main.personal.Order.allorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;

/**
 * Created by Administrator on 2017/9/2.
 * 待收货
 */

public class WaitReceiveDelegate extends MallDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_waitreceiver;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
