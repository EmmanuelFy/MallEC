package com.prcymy.ymy.ec.main.personal.Order.allorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.personal.Order.OrderListAdapter;
import com.prcymy.ymy.ec.main.personal.Order.OrderListDataConvert;
import com.prcymy.ymy.ec.main.personal.PersonalDelegate;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/1.
 */

public class AllOrderDelegate extends MallDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_allorder;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("order_list.json")
                .params("type",PersonalDelegate.ORDER_TYPE)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleltemEntity> data = new OrderListDataConvert().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
