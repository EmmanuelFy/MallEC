package com.prcymy.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.prcymy.ymy.activitys.ProxActivity;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.launcher.ILauncherListener;
import com.prcymy.ymy.ec.launcher.OnLauncherFinishTag;
import com.prcymy.ymy.ec.main.EcBottomDelegate;
import com.prcymy.ymy.ec.sign.ISignListener;
import com.prcymy.ymy.utils.StartBar;

import cn.jpush.android.api.JPushInterface;
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
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public MallDelegate setRootDelegate() {
        return new EcBottomDelegate();
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
