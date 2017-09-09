package com.prcymy.ymy.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.ItemType;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/8.
 */

public class Section extends DataConverter {

    @Override
    public ArrayList<MultipleltemEntity> convert() {
        final ArrayList<MultipleltemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONArray("data");

        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("cat_id");
            final String name = data.getString("cat_name");

            final MultipleltemEntity entity = MultipleltemEntity.builder()
                    .setIField(MultipleFields.ITEM_TYPE, ItemType.RIGHT_MENU_LIST)
                    .setIField(MultipleFields.ID, id)
                    .setIField(MultipleFields.TEXT, name)
                    .build();
            dataList.add(entity);

        }
        return dataList;
    }
}
