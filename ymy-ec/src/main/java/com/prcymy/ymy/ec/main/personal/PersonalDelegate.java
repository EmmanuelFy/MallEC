package com.prcymy.ymy.ec.main.personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.personal.Order.OrderDelegate;
import com.prcymy.ymy.ec.main.personal.address.AddressDelegate;
import com.prcymy.ymy.ec.main.personal.list.ItemType;
import com.prcymy.ymy.ec.main.personal.list.ListAdapter;
import com.prcymy.ymy.ec.main.personal.list.ListBean;
import com.prcymy.ymy.ec.main.personal.profile.UserProfileDelegate;
import com.prcymy.ymy.ec.main.personal.settings.SettingsDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PersonalDelegate extends BottomItemDelegate {

    //全部订单
    public static final int ALL_ORDER = 0;

    //待付款
    public static final int WAIT_PAY = 1;

    //待发货
    public static final int WAIT_DELIVER = 2;

    //待收货
    public static final int WAIT_RECEVICE = 3;

    //待评价
    public static final int WAIT_EVALUATE = 4;

    //售后
    public static final int AFTER_MARKET = 5;

    private Bundle bundle = new Bundle();

    public static final String ORDER_TYPE = "ORDER_TYPE";


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSetting = null;


    //全部订单
    @OnClick(R2.id.order_all)
    void orderAll(){
        setOrderNum(ALL_ORDER);
    }

    //待付款
    @OnClick(R2.id.ll_pay)
    void ll_pay(){
        setOrderNum(WAIT_PAY);
    }

    //待发货
    @OnClick(R2.id.ll_deliver)
    void ll_deliver(){
        setOrderNum(WAIT_DELIVER);
    }

    //待收货
    @OnClick(R2.id.ll_receive)
    void ll_receive(){
        setOrderNum(WAIT_RECEVICE);
    }

    //待评价
    @OnClick(R2.id.ll_evaluate)
    void ll_evaluate(){
        setOrderNum(WAIT_EVALUATE);
    }

    //售后
    @OnClick(R2.id.ll_after_market)
    void ll_after_market(){

        setOrderNum(AFTER_MARKET);
    }

    //头像点击
    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar(){
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    @OnClick(R2.id.tv_settings)
    void onClickSettings(){
        getParentDelegate().getSupportDelegate().start(new SettingsDelegate());
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean address = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_BOTTOM)
                .setId(1)
                .setText("收货地址")
                .setDelegate(new AddressDelegate())
                .setImageUrl(R.mipmap.ic_launcher)
                .build();



        final List<ListBean> data = new ArrayList<>();
        data.add(address);

        //设置Recyclerview
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRvSetting.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSetting.setAdapter(adapter);
        mRvSetting.addOnItemTouchListener(new PersnoalOnClickListener(this));
    }

    //跳转faragment
    private void setOrderNum(int position){
        OrderDelegate delegate = new OrderDelegate();
        bundle.putInt("position",position);
        delegate.setArguments(bundle);
        getParentDelegate().getSupportDelegate().start(delegate);
    }
}
