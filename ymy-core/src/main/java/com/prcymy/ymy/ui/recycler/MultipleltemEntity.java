package com.prcymy.ymy.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/8/8.
 * 数据转换
 */

public class MultipleltemEntity implements MultiItemEntity{


    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUENE = new ReferenceQueue<>();
    //数据的键值对
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE
            = new SoftReference<>(MULTIPLE_FIELDS,ITEM_QUENE);

    MultipleltemEntity(LinkedHashMap<Object,Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);

    }

    public static MultipleltemEntityBuilder builder(){
        return new MultipleltemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    /**
     * @param key
     * @param <T>
     * @return
     */
    //获取其他数据
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    /**
     *
     * @return 返回整个map
     */
    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }

    /**
     *  插入数据
     * @param key
     * @param value
     * @return 返回本类
     */
    public final MultipleltemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }
}
