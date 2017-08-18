package com.prcymy.ymy.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/18.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {

    private ShopCartAdapter mAdapter = null;

    //编辑和完成的标志
    private boolean isCheck = true;

    @BindView(R2.id.iv_shaop_cart)
    RecyclerView recyclerView = null;

    //编辑按钮
    @BindView(R2.id.tv_top_shop_cart_edit)
    AppCompatTextView editTv = null;

    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView payTv = null;
    @OnClick(R2.id.tv_shop_cart_pay)
    void payOnClick(){
        Log.i("---------","结算");
    }

    //移入收藏
    @BindView(R2.id.tv_shop_cart_select)
    AppCompatTextView selectTv = null;

    @OnClick(R2.id.tv_shop_cart_select)
    void selectOnClick() {
        Log.i("-----", "移入收藏");
    }

    //删除
    @BindView(R2.id.tv_shop_cart_delete)
    AppCompatTextView deleteTv = null;

    @OnClick(R2.id.tv_shop_cart_delete)
    void deleteTvOnClick() {
        Log.i("-----", "删除");
    }


    @OnClick(R2.id.tv_top_shop_cart_edit)
    void editText() {
        if (isCheck) {
            //赋值为false
            isCheck = false;
            editTv.setText("完成");
            //显示控件
            selectTv.setVisibility(View.VISIBLE);
            deleteTv.setVisibility(View.VISIBLE);
        } else {
            //赋值位true
            isCheck = true;
            editTv.setText("编辑");
            selectTv.setVisibility(View.GONE);
            deleteTv.setVisibility(View.GONE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart_data.json")
                .success(this)
                .build()
                .get();


    }


    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleltemEntity> data = new ShopCartDataConverter().setJsonData(response).convert();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        mAdapter = new ShopCartAdapter(data);
        recyclerView.setAdapter(mAdapter);

    }
}
