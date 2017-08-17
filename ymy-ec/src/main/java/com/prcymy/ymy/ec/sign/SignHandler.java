package com.prcymy.ymy.ec.sign;

import com.prcymy.ymy.app.AccountManager;
import com.prcymy.ymy.ec.database.DataBaseManger;
import com.prcymy.ymy.ec.database.UserProfile;

/**
 * Created by Administrator on 2017/8/4.
 */

public class SignHandler {

    //注册
    public static void onSignUp(String respone,ISignListener signListener) {
        /*final JSONObject profileJson = JSON.parseObject(respone).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String phone = profileJson.getString("name");
        final String avatar = profileJson.getString("name");
        final String gender = profileJson.getString("name");
        final String address = profileJson.getString("name");
        final String name = profileJson.getString("name");*/

        final long userId = 1231;
        final String phone = "12345678900";
        final String avatar = "http://www.dsdad.com";
        final String gender = "男";
        final String address = "广东广州";
        final String name = "哎呀";

        final UserProfile profile = new UserProfile(userId,name,phone,avatar,gender,address);
        DataBaseManger.getInstance().getDao().insert(profile);

        //已经注册成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

    }

    //登录
    public static void onSignIn(String respone,ISignListener signListener) {
        /*final JSONObject profileJson = JSON.parseObject(respone).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String phone = profileJson.getString("name");
        final String avatar = profileJson.getString("name");
        final String gender = profileJson.getString("name");
        final String address = profileJson.getString("name");
        final String name = profileJson.getString("name");*/

        final long userId = 1231;
        final String phone = "12345678900";
        final String avatar = "http://www.dsdad.com";
        final String gender = "男";
        final String address = "广东广州";
        final String name = "哎呀";

        final UserProfile profile = new UserProfile(userId,name,phone,avatar,gender,address);
        DataBaseManger.getInstance().getDao().insert(profile);

        //已经登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();


    }

}
