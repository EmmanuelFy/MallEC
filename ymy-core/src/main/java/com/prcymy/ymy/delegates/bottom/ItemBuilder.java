package com.prcymy.ymy.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/8/5.
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomBean, BottomItemDelegate> TIMES = new LinkedHashMap<>();



    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomBean bean, BottomItemDelegate delegate) {
        TIMES.put(bean, delegate);

        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomBean, BottomItemDelegate> items) {
        TIMES.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomBean, BottomItemDelegate> build() {
        return TIMES;
    }
}
