package com.prcymy.ymy.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.ItemType;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/8.
 */

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleltemEntity> convert() {

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text!=null){
                type = ItemType.TEXT;
            }else if (imageUrl != null && text == null){
                type = ItemType.IMAGE;
            }else if (imageUrl!=null){
                type = ItemType.TEXT_IMAGE;
            }else if (banners!= null){
                type = ItemType.BANNER;
                //Banner初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleltemEntity entity = MultipleltemEntity.builder()
                    .setIField(MultipleFields.ITEM_TYPE,type)
                    .setIField(MultipleFields.SPAN_SIZE,spanSize)
                    .setIField(MultipleFields.ID,id)
                    .setIField(MultipleFields.TEXT,text)
                    .setIField(MultipleFields.IMAGE_URL,imageUrl)
                    .setIField(MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
