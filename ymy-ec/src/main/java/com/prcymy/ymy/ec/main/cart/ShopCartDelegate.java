package com.prcymy.ymy.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/18.
 */

public class ShopCartDelegate extends BottomItemDelegate
        implements ISuccess,ICartItemListener {

    private ShopCartAdapter mAdapter = null;

    //购物车数量标记,要删除的
    private int mCurrentCount = 0;

    //总共Item的数量
    private int mTotalCount = 0;

    //编辑和完成的标志
    private boolean isCheck = true;

    @BindView(R2.id.iv_shaop_cart)
    RecyclerView recyclerView = null;

    //数量减少
    /*@BindView(R2.id.icon_item_minus)
    IconTextView minus = null;*/


    /*//数量加
    @BindView(R2.id.icon_item_plus)
    IconTextView plus = null;*/

    @BindView(R2.id.icon_shop_cart_selcet_all)
    IconTextView mIconSelectedAll = null;

    //全选
    @OnClick(R2.id.icon_shop_cart_selcet_all)
    void onClickAll(){
      final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0){
            mIconSelectedAll.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(),R.color.false_selected));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectedAll(true);

            //更新view的显示状态
            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
        }else {
            mIconSelectedAll.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(),R.color.app_main));
            mIconSelectedAll.setTag(0);
            mAdapter.setIsSelectedAll(false);

            //更新view的显示状态
            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
        }
    }

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
        final List<MultipleltemEntity> data = mAdapter.getData();

        //要删除的数据
        List<MultipleltemEntity> deleteEnties = new ArrayList<>();

        for (MultipleltemEntity entity : data){
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected){
                deleteEnties.add(entity);
            }
        }

        for (MultipleltemEntity entity: deleteEnties){
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount -1){
                removePosition = entityPosition - (mTotalCount - mCurrentCount);

            }else {
                removePosition = entityPosition;
            }

            if (removePosition <= mAdapter.getItemCount()){
                mAdapter.remove(entityPosition);
                mCurrentCount = mAdapter.getItemCount();

                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition,mAdapter.getItemCount());
            }

        }

        checkItemCount();

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

            //数量
           /* minus.setVisibility(View.VISIBLE);
            plus.setVisibility(View.VISIBLE);*/

        } else {
            //赋值位true
            isCheck = true;
            editTv.setText("编辑");
            selectTv.setVisibility(View.GONE);
            deleteTv.setVisibility(View.GONE);

            //数量
          /*  minus.setVisibility(View.GONE);
            plus.setVisibility(View.GONE);*/
        }
    }

    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStub = null;

    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView total = null;

    private  void checkItemCount(){
        final  int count = mAdapter.getItemCount();
        if (count == 0){
            final  View stubView = mStub.inflate();
            final  AppCompatTextView tvToBuy = (AppCompatTextView) stubView.findViewById(R.id.stub_no_item);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以到其他页面

                    Toast.makeText(_mActivity, "你该购物了", Toast.LENGTH_SHORT).show();

                }
            });

            recyclerView.setVisibility(View.GONE);

        }else {

            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectedAll.setTag(0);
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
        mAdapter.setICartItemListener(this);
        recyclerView.setAdapter(mAdapter);
        checkItemCount();
    }

    //价格计算
    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        total.setText(String.valueOf(price));
    }
}
