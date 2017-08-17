package com.prcymy.ymy.delegates.web;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/8/11.
 */

public class WebViewInitializer {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public WebView creatWebView(WebView webView) {

        WebView.setWebContentsDebuggingEnabled(true);

        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);

        //不能纵向滑动
        webView.setVerticalScrollBarEnabled(false);

        //允许截图
        webView.setDrawingCacheEnabled(true);

        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //初始化WebSettings
        final WebSettings settings = webView.getSettings();
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "mall");

        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        return webView;
    }

}
