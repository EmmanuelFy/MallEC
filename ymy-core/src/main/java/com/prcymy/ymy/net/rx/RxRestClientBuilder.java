package com.prcymy.ymy.net.rx;

import android.content.Context;

import com.prcymy.ymy.net.RestCreator;
import com.prcymy.ymy.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/7/31.
 * 传值操作
 */

public class RxRestClientBuilder {

    private String mUrl;

    private Map<String,Object> PARAMS = RestCreator.getParms();

    private RequestBody mBody;

    private Context mContext;

    private LoaderStyle mLoaderStyle;

    private File mFile;


    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }


    public final RxRestClientBuilder raw(String raw){

        this.mBody = RequestBody.create(MediaType.parse("applcation/json;charset=UTF-8"),raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotateMultipleIndicator;
        return this;
    }


    public  final RxRestClient build(){
        return new RxRestClient(mUrl,
                PARAMS,
                mBody,
                mFile,
                mContext,
                mLoaderStyle);
    }
}
