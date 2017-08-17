package com.prcymy.ymy.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.prcymy.ymy.delegates.MallDelegate;

/**
 * Created by Administrator on 2017/8/5.
 */

public abstract class BottomItemDelegate extends MallDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXEIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - mExitTime) > EXEIT_TIME) {
                Toast.makeText(getContext(), "双击退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
        }
        return true;
    }
}
