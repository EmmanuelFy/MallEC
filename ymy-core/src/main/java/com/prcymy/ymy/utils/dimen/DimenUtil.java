package com.prcymy.ymy.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.prcymy.ymy.app.Mall;

/**
 * Created by Administrator on 2017/8/1.
 */

public class DimenUtil {

    public static int getScreenWindth() {

        final Resources resources = Mall.getApplicationContext().getResources();

        //获取宽度
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {

        final Resources resources = Mall.getApplicationContext().getResources();

        //获取高度
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
