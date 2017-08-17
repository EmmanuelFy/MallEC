package com.prcymy.ymy.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/8/10.
 */

public abstract class WebDelegate extends MallDelegate implements IWebViewInitializer{

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUENE = new ReferenceQueue<>();
    private String mUrl = null;
    //
    private boolean mIsWebviewAbailable = false;

    private MallDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebview();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebview() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference
                        = new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUENE);

                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                mWebView.addJavascriptInterface(MallWebInterface.create(this), "mall");
                mIsWebviewAbailable = true;
            } else {
                throw new NullPointerException("Initializer is null");
            }


        }
    }

    public void setTopDelegate(MallDelegate delegate){
        mTopDelegate = delegate;
    }

    public MallDelegate getTopDelegate(){
        if (mTopDelegate == null){
            mTopDelegate = this;
        }

        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("webview is null");
        }

        return mIsWebviewAbailable ? mWebView : null;
    }

    public String getUrl(){
        if (mUrl == null){
            throw new NullPointerException("webview is null");
        }

        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebviewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
