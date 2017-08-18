package com.prcymy.ymy.delegates.web.event;

import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UndefindEvent extends Event {


    @Override
    public String execute(String parms) {
        if (getAction().equals("test")) {
            Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
            final WebView webView = getWebView();
            webView.post(new Runnable() {

                @Override
                public void run() {

                    webView.evaluateJavascript("nativeCall();",null);

                }
            });
        }
        return null;
    }
}
