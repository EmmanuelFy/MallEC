package com.prcymy.ymy.net.rx;

import android.content.Context;

import com.prcymy.ymy.net.HttpMethod;
import com.prcymy.ymy.net.RestCreator;
import com.prcymy.ymy.ui.loader.LoaderStyle;
import com.prcymy.ymy.ui.loader.MallLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/7/31.
 * 请求实现类
 */

public class RxRestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARMS = RestCreator.getParms();

    private final RequestBody BODY;

    private LoaderStyle LOADER_STYLE;

    private final File FILE;

    private Context CONTEXT;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod mHttpMethod) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        if (LOADER_STYLE != null) {
            MallLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (mHttpMethod) {
            case GET:
                observable = service.get(URL, PARMS);
                break;
            case POST:
                observable = service.post(URL, PARMS);
                break;
            case PSOT_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARMS);
                break;
            case PUT_RAM:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.
                        create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body= MultipartBody.Part
                        .createFormData("file",FILE.getName());
                observable = service.upload(URL,body);
                break;
            default:

                break;
        }

        return observable;
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {

        if (BODY == null) {
           return request(HttpMethod.POST);
        } else {
            if (!PARMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }

            return request(HttpMethod.PSOT_RAW);
        }
    }

    public final Observable<String> put() {

        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }

            return request(HttpMethod.PUT_RAM);
        }
    }

    public final Observable<String> delete() {
        return  request(HttpMethod.DELETE);
    }

    public final Observable<ResponseBody> download(){
        return RestCreator.getRxRestService().download(URL,PARMS);
    }
}
