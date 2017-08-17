package com.prcymy.ymy.app;

import com.prcymy.ymy.utils.storage.MallPreference;

/**
 * Created by Administrator on 2017/8/4.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存登录状态
    public static void setSignState(boolean state){
        MallPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    //检查登录状态
    public static boolean isSignIn(){
        return MallPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //判断是否登录
    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
