package com.prcymy.ymy.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MallWebInterface {
    private final WebDelegate DELGATE;

    public MallWebInterface(WebDelegate DELGATE) {
        this.DELGATE = DELGATE;
    }

    static MallWebInterface create(WebDelegate delegate){
        return new MallWebInterface(delegate);
    }

    //js返回值
    public String event(String parms){
        final String action = JSON.parseObject(parms).getString("action");
        return null;
    }
}
