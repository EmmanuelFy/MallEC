package com.prcymy.ymy_rtb.main.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.utils.StartBar;
import com.prcymy.ymy_rtb.R;

/**
 * Created by Administrator on 2017/8/16.
 */

public class UserDemand extends MallDelegate{


    @Override
    public Object setLayout() {
        return R.layout.delegate_userdemand_need;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        StartBar.MIUISetStatusBarLightMode(_mActivity,false);
    }
}
