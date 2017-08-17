package com.prcymy.ymy.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/8/8.
 */

public class MultipleltemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleltemEntityBuilder() {
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleltemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleltemEntityBuilder setIField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleltemEntityBuilder setIFields(WeakHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleltemEntity build() {
        return new MultipleltemEntity(FIELDS);
    }
}
