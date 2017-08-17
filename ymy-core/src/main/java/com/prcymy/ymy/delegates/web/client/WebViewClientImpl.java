package com.prcymy.ymy.delegates.web.client;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.prcymy.ymy.delegates.web.WebDelegate;
import com.prcymy.ymy.delegates.web.route.Router;

/**
 * Created by Administrator on 2017/8/11.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        Log.i("shouldOverrideUrlLoading", url);

        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }
}
