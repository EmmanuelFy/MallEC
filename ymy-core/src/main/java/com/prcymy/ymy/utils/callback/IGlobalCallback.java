package com.prcymy.ymy.utils.callback;

import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/9/5.
 */

//全局回调
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
