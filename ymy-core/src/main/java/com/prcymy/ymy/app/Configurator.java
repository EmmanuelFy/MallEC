package com.prcymy.ymy.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.prcymy.ymy.delegates.web.event.Event;
import com.prcymy.ymy.delegates.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/28.
 * 配置文件
 */

public class Configurator {
    //主配置信息
    private static final HashMap<Object, Object> MALL_CONFIGS = new HashMap<>();

    //字体图标库
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //构造方法
    private Configurator() {
        //进入开始配置   默认未完成
        MALL_CONFIGS.put(ConfigType.CONFIG_READY, false);

    }

    /**
     * 静态内部类单例初始化
     *
     * @return
     */

    //单列回调
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    //单例 全局唯一
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //单例完成后 告诉配置文件  初始化完成
    public final void configure() {

        initIcons();
        MALL_CONFIGS.put(ConfigType.CONFIG_READY, true);
        Utils.init((Application) Mall.getApplicationContext());
    }

    //返回配置信息 给mall调用
    final HashMap<Object, Object> getMallConfig() {
        return MALL_CONFIGS;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initalizzer = Iconify.with(ICONS.get(0));
            for (int i = 1 ; i < ICONS.size(); i++) {
                initalizzer.with(ICONS.get(i));
            }
        }
    }

    //初始化主域名
    public final Configurator withApiHost(String host) {
        MALL_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    //初始化javascript
    public Configurator withJavascriptInterface(@NonNull String name){
        MALL_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE,name);
        return this;
    }

    //初始化event
    public Configurator withWebEvent(@NonNull String name, @NonNull Event event){
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name,event);
        return this;
    }

    //初始化cookie
    public Configurator withWebHost(String webHost){
        MALL_CONFIGS.put(ConfigType.WEB_HOST,webHost);
        return this;
    }

    //检测初始化是否完成
    private void checkConfiguration() {
        final boolean isReady = (boolean) MALL_CONFIGS.get(ConfigType.CONFIG_READY);

        if (!isReady) {
            throw new RuntimeException("Configator is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigurtion(Object key) {
        checkConfiguration();
        return (T) MALL_CONFIGS.get(key);
    }
}
