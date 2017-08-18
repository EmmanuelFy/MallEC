package com.prcymy.ymy.delegates.web.client;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.prcymy.ymy.app.ConfigType;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.delegates.IPageLoadListener;
import com.prcymy.ymy.delegates.web.WebDelegate;
import com.prcymy.ymy.delegates.web.route.Router;
import com.prcymy.ymy.utils.storage.MallPreference;

/**
 * Created by Administrator on 2017/8/11.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        Log.i("shouldOverrideUrlLoading", url);

        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    //同步cookie
    public void syncCookie(){
        final CookieManager manager = CookieManager.getInstance();
        //这里饿cookie和Api请求的是不一样的,这个在网页不可见
        final String webHost = Mall.getConfiguration(ConfigType.WEB_HOST);
        if (webHost!=null){
            final String cookieStr = manager.getCookie(webHost);
            if (cookieStr!=null && cookieStr.equals("")){
                MallPreference.addCustomAppProfile("cookie",cookieStr);
            }

        }else {
            throw new NullPointerException("webHost is null.....");
        }

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }
        //加载等待dialog
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener!= null){
            mIPageLoadListener.onLoadEnd();
        }

        //结束等待Dialog
    }

}
