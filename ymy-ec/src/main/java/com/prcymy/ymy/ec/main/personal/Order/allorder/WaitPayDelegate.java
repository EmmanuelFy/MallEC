package com.prcymy.ymy.ec.main.personal.Order.allorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;

/**
 * Created by Administrator on 2017/9/2.
 * 待支付
 */

public class WaitPayDelegate extends MallDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_waitpay;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Toast.makeText(_mActivity, "pay", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
