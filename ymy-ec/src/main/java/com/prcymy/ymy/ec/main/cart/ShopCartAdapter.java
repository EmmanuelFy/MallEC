package com.prcymy.ymy.ec.main.cart;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;
import com.prcymy.ymy.app.Mall;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;

    private ICartItemListener mICartItemListener = null;

    private double mTotalPrice = 0.00;
    private IconTextView iconSelected;

    protected ShopCartAdapter(List<MultipleltemEntity> data) {
        super(data);

        //添加购物布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);

        //初始化总价
        for (MultipleltemEntity entity : data) {

            //可以判断选中状态  进行价格总计
            final boolean field = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (field){
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double total = price * count;

                mTotalPrice = mTotalPrice + total;
                mICartItemListener.onItemClick(mTotalPrice);
            }

        }
    }


    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setICartItemListener(ICartItemListener mICartItemListener) {
        this.mICartItemListener = mICartItemListener;
    }

    //
    public double getTotalPrice(){
        return mTotalPrice;
    }


    @Override
    protected void convert(MultipleViewHolder holder, final MultipleltemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);

                //取出控件
                final AppCompatImageView thumbImageView = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView mTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView mDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView mPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView mCount = holder.getView(R.id.tv_item_shop_cart_content);
                final IconTextView minus = holder.getView(R.id.icon_item_minus);
                final IconTextView plus = holder.getView(R.id.icon_item_plus);


                //赋值
                Glide.with(mContext).load(thumb).into(thumbImageView);
                mTitle.setText(title);
                mDesc.setText(desc);
                mPrice.setText(String.valueOf(price));
                mCount.setText(String.valueOf(count));

                //zai左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                iconSelected = holder.getView(R.id.icon_item_shop_cart);

                if (isSelected) {
                    iconSelected.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(), R.color.app_main));
                } else {
                    iconSelected.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(), R.color.false_selected));
                }


                iconSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconSelected.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(), R.color.false_selected));
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconSelected.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });

                //数量减少
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(mCount.getText().toString()) > 1) {
                            //告诉服务器需要减少数量
                            RestClient.builder()
                                    .url("shop_cart_data.json")
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(mCount.getText().toString());
                                            countNum--;
                                            mCount.setText(String.valueOf(countNum));
                                            if (mICartItemListener != null) {
                                                //所有总价
                                                mTotalPrice = mTotalPrice - price;
                                                //当前单个产品的总价
                                                final double itemTotal = countNum * price;
                                                mICartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }

                    }
                });

                //数量增加
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        //告诉服务器需要增加数量
                        RestClient.builder()
                                .url("shop_cart_data.json")
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int countNum = Integer.parseInt(mCount.getText().toString());
                                        countNum++;
                                        mCount.setText(String.valueOf(countNum));

                                        if (mICartItemListener != null) {
                                            //所有总价
                                            mTotalPrice = mTotalPrice + price;
                                            //当前单个产品的总价
                                            final double itemTotal = countNum * price;
                                            mICartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                break;
            default:

                break;
        }

    }

    public void setIcon(){
        iconSelected.setTextColor(ContextCompat.getColor(Mall.getApplicationContext(), R.color.false_selected));
    }
}
