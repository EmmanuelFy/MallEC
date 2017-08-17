package com.prcymy.ymy.net;

import android.content.Context;

import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.IRequest;
import com.prcymy.ymy.net.callback.ISuccess;
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

public class RestClientBuilder {

    private String mUrl;

    private Map<String,Object> PARAMS = RestCreator.getParms();

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IFail mIFail;

    private IError mIError;

    private RequestBody mBody;

    private Context mContext;

    private LoaderStyle mLoaderStyle;

    private File mFile;

    private String mDownloadDir = null;
    private String mExtenstion = null;
    private String mName = null;


    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name){
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir){
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension){
        this.mExtenstion = extension;
        return this;
    }

    public final RestClientBuilder raw(String raw){

        this.mBody = RequestBody.create(MediaType.parse("applcation/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){

        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest){

        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder fail(IFail iFail){

        this.mIFail = iFail;
        return this;
    }

    public final RestClientBuilder error(IError iError){

        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


    public  final RestClient build(){
        return new RestClient(mUrl,
                PARAMS,
                mIRequest,
                mDownloadDir,
                mExtenstion,
                mName,
                mISuccess,
                mIFail,
                mIError,
                mBody,
                mFile,
                mContext,
                mLoaderStyle);
    }
}
