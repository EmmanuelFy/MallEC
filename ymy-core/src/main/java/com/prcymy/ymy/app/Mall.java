package com.prcymy.ymy.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/28.
 */

public final class Mall {

    /**
     * 返回配置信息
     *
     * @param context 全局上下文
     * @return
     */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATON_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();

    }

    // 配置信息返回给init调用
    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getMallConfig();
    }


    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATON_CONTEXT);
    }
}
