package com.prcymy.ymy.delegates.bottom;

/**
 * Created by Administrator on 2017/8/5.
 */

public class BottomBean {

    private int ICON;
    private CharSequence TITLE;

    public BottomBean(int ICON, CharSequence TITLE) {
        this.ICON = ICON;
        this.TITLE = TITLE;
    }

    public int getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
