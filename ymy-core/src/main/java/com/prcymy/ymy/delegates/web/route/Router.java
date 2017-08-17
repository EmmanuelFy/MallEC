package com.prcymy.ymy.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.delegates.web.WebDelegate;
import com.prcymy.ymy.delegates.web.WebDelegateImpl;

/**
 * Created by Administrator on 2017/8/11.
 * 路由
 */

public class Router {

    public Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        //必须从第一个加载,才会是全屏
        //final MallDelegate topdelegate = delegate.getTopDelegate();
        final MallDelegate parentDelegate = delegate.getParentDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        if (parentDelegate == null) {
            delegate.start(webDelegate);
        } else {
            parentDelegate.start(webDelegate);
        }

        return true;
    }



    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WwebView is null");
        }
    }

    //加载本地资源文件
    private void loadlocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);

    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAboutUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadlocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context contenxt, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(contenxt, intent, null);
    }
}
