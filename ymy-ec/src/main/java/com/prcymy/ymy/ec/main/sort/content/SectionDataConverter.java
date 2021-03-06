package com.prcymy.ymy.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class SectionDataConverter {

    final List<SectionBean> converter(String json){

        final List<SectionBean> dataList = new ArrayList<>();
       final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("cat_id");
            final String title = data.getString("cat_name");

            //添加title
            final SectionBean sectionBean = new SectionBean(true,title);
            sectionBean.setmId(id);
            sectionBean.setmIsMore(true);
            dataList.add(sectionBean);

            final JSONArray goods = data.getJSONArray("goods");

            //商品内容循环
            for (int j = 0; j < goods.size(); j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");

                //获取内容
                final SectionContentItemEntity itemEntity
                        = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);

                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }

            //商品内容循环结束
        }

        //Section循环结束

        return dataList;
    }



}
