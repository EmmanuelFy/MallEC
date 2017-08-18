package com.prcymy.ymy.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/18.
 */

public class ShopCartDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleltemEntity> convert() {

        final ArrayList<MultipleltemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleltemEntity entity = MultipleltemEntity.builder()
                    .setIField(MultipleFields.ITEM_TYPE,ShopCartItemType.SHOP_CART_ITEM)
                    .setIField(MultipleFields.IMAGE_URL,thumb)
                    .setIField(MultipleFields.ID,id)
                    .setIField(ShopCartItemFields.TITLE,title)
                    .setIField(ShopCartItemFields.DESC,desc)
                    .setIField(ShopCartItemFields.COUNT,count)
                    .setIField(ShopCartItemFields.PRICE,price)
                    .build();

            dataList.add(entity);

        }


        return dataList;
    }
}
