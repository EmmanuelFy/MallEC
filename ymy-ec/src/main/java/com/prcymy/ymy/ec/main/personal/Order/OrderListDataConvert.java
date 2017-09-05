package com.prcymy.ymy.ec.main.personal.Order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/1.
 */

public class OrderListDataConvert extends DataConverter {


    @Override
    public ArrayList<MultipleltemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title =data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");

            final MultipleltemEntity entity = MultipleltemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setIField(MultipleFields.ID,id)
                    .setIField(MultipleFields.IMAGE_URL,thumb)
                    .setIField(MultipleFields.TITLE,title)
                    .setIField(OrderItemFields.PRICE,price)
                    .setIField(OrderItemFields.TIME,time)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
