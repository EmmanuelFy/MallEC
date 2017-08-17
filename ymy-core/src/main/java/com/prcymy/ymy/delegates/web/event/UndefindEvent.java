package com.prcymy.ymy.delegates.web.event;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UndefindEvent extends Event {


    @Override
    public String execute(String parms) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    webView.evaluateJavascript("send();",null);
                }
            });
        }
        return null;
    }
}
