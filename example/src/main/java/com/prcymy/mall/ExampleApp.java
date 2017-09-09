package com.prcymy.mall;

import android.app.Application;
import android.support.annotation.Nullable;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.prcymy.mall.event.TestEvent;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.ec.database.DataBaseManger;
import com.prcymy.ymy.ec.icon.FontEcModel;
import com.prcymy.ymy.utils.callback.CallbackManager;
import com.prcymy.ymy.utils.callback.CallbackType;
import com.prcymy.ymy.utils.callback.IGlobalCallback;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/7/28.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //http://192.168.8.167/RestServer/data/
        //http://www.ydmgx.com/
        Mall.init(this)
                .withApiHost("http://www.ydmgx.com/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModel())
                .withJavascriptInterface("mall")
                .withWebEvent("test", new TestEvent())
                .withWebHost("http://www.baidu.com/")
                .configure();
        DataBaseManger.getInstance().init(this);

        //极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance().addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                //如果是关闭的状态就开启
                if (JPushInterface.isPushStopped(Mall.getApplicationContext())){
                    //开启极光推送
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(Mall.getApplicationContext());

                }
            }
        })
       .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                //如果没有停止,,我们就停止
                if (!JPushInterface.isPushStopped(Mall.getApplicationContext())){
                    JPushInterface.stopPush(Mall.getApplicationContext());
                }
            }
        });
    }
}

