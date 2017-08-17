package com.prcymy.ymy.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.prcymy.ymy.delegates.web.client.WebViewClientImpl;
import com.prcymy.ymy.delegates.web.route.RouteKeys;
import com.prcymy.ymy.delegates.web.route.Router;

/**
 * Created by Administrator on 2017/8/11.
 */

public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;

    }


    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null){
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());

        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return null;
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return null;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return null;
    }
}
