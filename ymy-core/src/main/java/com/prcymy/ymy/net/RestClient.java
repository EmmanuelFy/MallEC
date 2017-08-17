package com.prcymy.ymy.net;

import android.content.Context;

import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.IRequest;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.net.callback.RequestCallbacks;
import com.prcymy.ymy.net.download.DownloadHandler;
import com.prcymy.ymy.ui.loader.LoaderStyle;
import com.prcymy.ymy.ui.loader.MallLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/7/31.
 * 请求实现类
 */

public class RestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARMS = RestCreator.getParms();

    private final IRequest REQUST;

    private final String DOWNLOAD_DIR;

    private final String EXTENSICN;

    private final String NAME;

    private final ISuccess SUCCESS;

    private final IFail FAIL;

    private final IError ERROR;

    private final RequestBody BODY;

    private LoaderStyle LOADER_STYLE;

    private final File FILE;

    private Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      String download_dir,
                      String extensicn,
                      String name,
                      ISuccess success,
                      IFail fail,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARMS.putAll(params);
        this.REQUST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSICN = extensicn;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAIL = fail;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod mHttpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUST != null) {
            REQUST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            MallLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (mHttpMethod) {
            case GET:
                call = service.get(URL, PARMS);
                break;
            case POST:
                call = service.post(URL, PARMS);
                break;
            case PSOT_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARMS);
                break;
            case PUT_RAM:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body= MultipartBody.Part.createFormData("file",FILE.getName());
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:

                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUST,
                SUCCESS,
                FAIL,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {

        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }

            request(HttpMethod.PSOT_RAW);
        }
    }

    public final void put() {

        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }

            request(HttpMethod.PUT_RAM);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownloadHandler(URL,REQUST,DOWNLOAD_DIR,EXTENSICN,NAME,SUCCESS,FAIL,ERROR).handlerDownload();
    }
}
