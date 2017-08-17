package com.prcymy.mall;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
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

        Mall.init(this)
                .withApiHost("http://192.168.8.178:8080/RestServer/data/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModel())
                .configure();
        DataBaseManger.getInstance().init(this);
    }
}
