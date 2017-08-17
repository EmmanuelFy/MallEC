package com.prcymy.ymy.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017/7/28.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue715');

    private char characetr;

    EcIcons(char characetr) {
        this.characetr = characetr;
    }

    @Override
    public String key() {

        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return 0;
    }
}
