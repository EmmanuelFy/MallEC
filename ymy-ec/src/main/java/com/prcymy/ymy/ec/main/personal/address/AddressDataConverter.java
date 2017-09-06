package com.prcymy.ymy.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/6.
 */

public class AddressDataConverter extends DataConverter {



    @Override
    public ArrayList<MultipleltemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        int size = array.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleltemEntity entity = MultipleltemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setIField(MultipleFields.ID,id)
                    .setIField(MultipleFields.NAME,name)
                    .setIField(AddressItemFields.PHONE,phone)
                    .setIField(AddressItemFields.ADDRESS,address)
                    .setIField(MultipleFields.TAG,isDefault)
                    .build();
            ENTITIES.add(entity);

        }


        return ENTITIES;
    }
}
