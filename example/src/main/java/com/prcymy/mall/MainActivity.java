package com.prcymy.mall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.prcymy.ymy.activitys.ProxActivity;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.launcher.ILauncherListener;
import com.prcymy.ymy.ec.launcher.LauncherDelgate;
import com.prcymy.ymy.ec.launcher.OnLauncherFinishTag;
import com.prcymy.ymy.ec.main.EcBottomDelegate;
import com.prcymy.ymy.ec.sign.ISignListener;
import com.prcymy.ymy.ec.sign.SignInDelegate;
import com.prcymy.ymy.ec.sign.SignUpDelegate;
import com.prcymy.ymy.utils.StartBar;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

import static me.yokeyword.fragmentation.ISupportFragment.SINGLETASK;

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

        Mall.getConfigurator().withActivity(this);
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
        return new LauncherDelgate();
    }

    @Override
    public void onSignInSuccess() {
            //登陆成功后操作

    }

    @Override
    public void onSignUpSuccess() {
        //注册成功后操作
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLauncherFinsh(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:

                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }
}
