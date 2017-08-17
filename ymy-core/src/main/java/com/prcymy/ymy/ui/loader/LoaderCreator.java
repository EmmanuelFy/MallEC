package com.prcymy.ymy.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/8/1.
 */

public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView creat(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setClickable(false);
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        avLoadingIndicatorView.setIndicatorColor(android.R.color.darker_gray);
        return avLoadingIndicatorView;

    }

    private static Indicator getIndicator(String name){
        if (name == null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")){
            final String defaultPackName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackName)
                    .append(".indicators")
                    .append(".");
        }

        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass. newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
