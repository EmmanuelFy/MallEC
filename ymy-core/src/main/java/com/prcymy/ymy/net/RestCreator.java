package com.prcymy.ymy.net;


import com.prcymy.ymy.app.ConfigType;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.net.rx.RxRestService;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/7/31.
 * 构建请求
 */

public class RestCreator {

   private static final class ParamsHolder{
       public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
   }

   public static WeakHashMap<String,Object> getParms(){
       return ParamsHolder.PARAMS;
   }


    //单列模式 全局唯一
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Mall.getConfigurations().get(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    //设置超时时间
    private static final class OkhttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


    //service接口
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    //获取请求对象
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    //service接口
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
   }

    //获取请求对象
    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }
}
