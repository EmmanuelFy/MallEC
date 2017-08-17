package com.prcymy.ymy.delegates.web;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.prcymy.ymy.delegates.web.event.Event;
import com.prcymy.ymy.delegates.web.event.EventManager;

/**
 * Created by Administrator on 2017/8/10.
 */

final class MallWebInterface {
    private final WebDelegate DELGATE;

    public MallWebInterface(WebDelegate DELGATE) {
        this.DELGATE = DELGATE;
    }

    static MallWebInterface create(WebDelegate delegate){
        return new MallWebInterface(delegate);
    }

    //js返回值
    @JavascriptInterface
    public String event(String parms){
        final String action = JSON.parseObject(parms).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);

        if (event!=null){
            Log.i("--------",parms+"---"+action);
            event.setAction(action);
            event.setDelegate(DELGATE);
            event.setContext(DELGATE.getContext());
            event.setUrl(DELGATE.getUrl());

            return event.execute(parms);
        }
        return null;
    }
}
