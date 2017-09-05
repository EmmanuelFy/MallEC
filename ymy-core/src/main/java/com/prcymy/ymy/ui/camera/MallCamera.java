package com.prcymy.ymy.ui.camera;

/**
 * Created by Administrator on 2017/9/4.
 */

import android.net.Uri;

import com.prcymy.ymy.delegates.PermissionCheckerDelegate;
import com.prcymy.ymy.utils.file.FileUtil;

/**
 * 照片调用类
 */
public class MallCamera {

    public static Uri createCropFile(){
        return Uri.parse(FileUtil.createFile("crop_image",FileUtil.getFileNameByTime("IMG","JPG")).getPath());

    }
    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
