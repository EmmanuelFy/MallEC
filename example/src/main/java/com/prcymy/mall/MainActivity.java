package com.prcymy.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.prcymy.ymy.activitys.ProxActivity;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.launcher.ILauncherListener;
import com.prcymy.ymy.ec.launcher.OnLauncherFinishTag;
import com.prcymy.ymy.ec.sign.ISignListener;
import com.prcymy.ymy.utils.StartBar;
import com.prcymy.ymy_rtb.main.home.CompetitiveDelegate;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxActivity implements
        ISignListener,
        ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        StatusBarCompat.translucentStatusBar(this, true);
        StartBar.MIUISetStatusBarLightMode(this,true);
    }

    @Override
    public MallDelegate setRootDelegate() {
        return new CompetitiveDelegate();
    }

    @Override
    public void onSignInSuccess() {


    }

    @Override
    public void onSignUpSuccess() {


    }

    @Override
    public void onLauncherFinsh(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:

                break;
            case NOT_SIGNED:

                break;
        }
    }
}
