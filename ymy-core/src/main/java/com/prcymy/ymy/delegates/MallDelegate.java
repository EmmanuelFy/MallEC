package com.prcymy.ymy.delegates;

/**
 * Created by Administrator on 2017/7/29.
 */

public abstract class MallDelegate extends PermissionCheckerDelegate {


    public <T extends MallDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
