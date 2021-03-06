package com.prcymy.ymy.net.interceptors;

import com.prcymy.ymy.utils.storage.MallPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/18.
 */

public class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(MallPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        // 始原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie",s);
                    }
                });
        return chain.proceed(builder.build());
    }
}
