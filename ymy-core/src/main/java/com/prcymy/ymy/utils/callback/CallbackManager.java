package com.prcymy.ymy.utils.callback;

import java.util.Objects;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/9/5.
 */

public class CallbackManager {

    private static final WeakHashMap<Object,IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class  Holder{
        private static final CallbackManager INTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance(){
        return Holder.INTANCE;
    }

    public CallbackManager addCallback(Object tag,IGlobalCallback callback){
        CALLBACKS.put(tag,callback);
        return this;
    }
    public IGlobalCallback getCallback(Object tag){
        return CALLBACKS.get(tag);
    }
}
