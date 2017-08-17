package com.prcymy.ymy_rtb.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy_rtb.R;
import com.prcymy.ymy_rtb.R2;
import com.prcymy.ymy_rtb.main.user.UserDemand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/16.
 */

public class CompetitiveDelegate extends MallDelegate implements OnItemClickListener{


    private List<Integer> INTEGERS = new ArrayList<>();

    @BindView(R2.id.banner_competivie)
    ConvenientBanner<String> convenientBanner = null;

    @BindView(R2.id.btn_servicer)
    AppCompatButton buttonservicer = null;
    @OnClick(R2.id.btn_servicer)
    void btn_servicer(){

    }


    @BindView(R2.id.btn_user)
    AppCompatButton buttonuser = null;
    @OnClick(R2.id.btn_user)
    void btn_user(){
        start(new UserDemand());
    }

    private void initBanner(){
        /*RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //获取到网络banner图片

                    }
                })
                .build()
                .post();*/
        //控件，图片集合，上下文
        //BannerCreator.setDefault(convenientBanner,);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegat_competivie_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    //banner点击事件
    @Override
    public void onItemClick(int position) {
        Log.i("========",position+"");
    }
}
