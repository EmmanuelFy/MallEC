package com.prcymy.mall;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.prcymy.mall.event.TestEvent;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.ec.database.DataBaseManger;
import com.prcymy.ymy.ec.icon.FontEcModel;

/**
 * Created by Administrator on 2017/7/28.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
                                        //http://192.168.8.178:8080/RestServer/data/
        Mall.init(this)
                .withApiHost("http://www.ydmgx.com/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModel())
                .withJavascriptInterface("mall")
                .withWebEvent("test",new TestEvent())
                .withWebHost("http://www.baidu.com/")
                .configure();
        DataBaseManger.getInstance().init(this);
    }
}
