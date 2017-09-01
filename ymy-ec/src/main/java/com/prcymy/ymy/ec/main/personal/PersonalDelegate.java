package com.prcymy.ymy.ec.main.personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.personal.Order.OrderDelegate;
import com.prcymy.ymy.ec.main.personal.list.ItemType;
import com.prcymy.ymy.ec.main.personal.list.ListAdapter;
import com.prcymy.ymy.ec.main.personal.list.ListBean;

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


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSetting = null;


    //全部订单
    @OnClick(R2.id.order_all)
    void orderAll(){
        getParentDelegate().getSupportDelegate().start(new OrderDelegate(ALL_ORDER));
    }

    @OnClick(R2.id.tv_fk)
    void tv_fk(){
        getParentDelegate().getSupportDelegate().start(new OrderDelegate(WAIT_PAY));
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean address = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置Recyclerview
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSetting.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSetting.setAdapter(adapter);
    }
}
