package com.prcymy.ymy.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.ItemType;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/8/9.
 */

public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleltemEntity> convert() {

        final ArrayList<MultipleltemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("cat_id");
            final String name = data.getString("cat_name");

            final MultipleltemEntity entity = MultipleltemEntity.builder()
                    .setIField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setIField(MultipleFields.ID,id)
                    .setIField(MultipleFields.TEXT,name)
                    .setIField(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);

            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG,true);

        }
        return dataList;
    }
}
