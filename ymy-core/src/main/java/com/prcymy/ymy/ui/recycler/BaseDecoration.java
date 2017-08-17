package com.prcymy.ymy.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by Administrator on 2017/8/8.
 */

public class BaseDecoration extends DividerItemDecoration {

    public BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookuoImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color, size);
    }
}
