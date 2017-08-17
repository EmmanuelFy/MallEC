package com.prcymy.ymy.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.prcymy.ymy.R;
import com.prcymy.ymy.utils.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/1.
 */

public class MallLoader {

    private static final int LOADER_SIZE_SCALE = 6;
    private static final int LOADER_OFFEST_SCALE = 10;

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.creat(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int devcewidth = DimenUtil.getScreenWindth();
        int devceheight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow !=null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = devcewidth / LOADER_SIZE_SCALE;
            lp.height = devceheight / LOADER_SIZE_SCALE;
            lp.height = lp.height+devceheight / LOADER_OFFEST_SCALE;
            lp.gravity = Gravity.CENTER;
        }

        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context);
    }

    public static void stopLoding(){
        for (AppCompatDialog dialog: LOADERS) {
            if (dialog.isShowing()){
                //带回调的消失
               dialog.cancel();
                //不带回调的消失
                dialog.dismiss();
            }
        }
    }
}
