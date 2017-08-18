package com.prcymy.ymy.ec.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {


    protected ShopCartAdapter(List<MultipleltemEntity> data) {
        super(data);

        //添加购物布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleltemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()){
            case ShopCartItemType.SHOP_CART_ITEM:
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);

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

                break;
            default:

                break;
        }

    }
}
