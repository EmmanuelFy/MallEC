package com.prcymy.ymy.net.callback;

import android.os.Handler;

import com.prcymy.ymy.ui.loader.LoaderStyle;
import com.prcymy.ymy.ui.loader.MallLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/31.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUST;

    private final ISuccess SUCCESS;

    private final IFail FAIL;

    private final IError ERROR;

    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IFail fail,
                            IError error,
                            LoaderStyle loaderStyle) {
        this.REQUST = request;
        this.SUCCESS = success;
        this.FAIL = fail;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MallLoader.stopLoding();
                }
            }, 10000);
        }
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAIL != null) {
            FAIL.onFail();
        }

        if (REQUST != null) {
            REQUST.onRequstEnd();
        }

        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            MallLoader.stopLoding();
        }
    }
}
