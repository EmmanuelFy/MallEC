package com.prcymy.ymy.net.download;

import android.os.AsyncTask;

import com.prcymy.ymy.net.RestCreator;
import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.IRequest;
import com.prcymy.ymy.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/1.
 */

public class DownloadHandler {
    private final String URL;

    private static final WeakHashMap<String, Object> PARMS = RestCreator.getParms();

    private final IRequest REQUST;

    private final String DOWNLOAD_DIR;

    private final String EXTENSICN;

    private final String NAME;

    private final ISuccess SUCCESS;

    private final IFail FAIL;

    private final IError ERROR;

    public DownloadHandler(String url,
                           IRequest requst,
                           String download_dir,
                           String extensicon,
                           String name,
                           ISuccess success,
                           IFail fail,
                           IError error) {
        this.URL = url;
        this.REQUST = requst;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSICN = extensicon;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAIL = fail;
        this.ERROR = error;
    }

    public final void handlerDownload(){

        if (REQUST!= null){
            REQUST.onRequestStart();
        }

        RestCreator.getRestService().download(URL,PARMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       if (response.isSuccessful()){
                           final SaveFileTask task = new SaveFileTask(REQUST,SUCCESS);
                           task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,response,NAME);

                           //这里注意判断,否则文件下载不全
                           if (task.isCancelled()){
                               if (REQUST != null){
                                   REQUST.onRequstEnd();
                               }
                           }
                       }else {
                           if (ERROR != null){
                               ERROR.onError(response.code(),response.message());
                           }
                       }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAIL!=null){
                            FAIL.onFail();
                        }
                    }
                });


    }

}
