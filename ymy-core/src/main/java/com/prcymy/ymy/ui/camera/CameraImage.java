package com.prcymy.ymy.ui.camera;

/**
 * Created by Administrator on 2017/9/4.
 */

import android.net.Uri;

/**
 * 存储中间信息
 */
public final class CameraImage {
    private Uri mPath = null;

    private static final CameraImage INSTANCE = new CameraImage();

    public static CameraImage getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
