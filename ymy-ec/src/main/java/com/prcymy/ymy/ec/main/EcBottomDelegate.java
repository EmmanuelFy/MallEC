package com.prcymy.ymy.ec.main;

import android.graphics.Color;

import com.prcymy.ymy.delegates.bottom.BaseBottomDelegate;
import com.prcymy.ymy.delegates.bottom.BottomBean;
import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.delegates.bottom.ItemBuilder;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.main.discover.DiscoverDelegate;
import com.prcymy.ymy.ec.main.index.IndexDelegate;
import com.prcymy.ymy.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/8/5.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomBean(R.drawable.mainpage_home_nor_ic,"主页"),new IndexDelegate());
        items.put(new BottomBean(R.drawable.mainpage_category_nor_ic,"分类"),new SortDelegate());
        items.put(new BottomBean(R.drawable.mainpage_topic_nor_ic,"发现"),new DiscoverDelegate());
        items.put(new BottomBean(R.drawable.all_shopping_cart_nor_ic,"购物车"),new IndexDelegate());
        items.put(new BottomBean(R.drawable.mainpage_person_nor_ic,"我的"),new IndexDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#eb4343");
    }
}
