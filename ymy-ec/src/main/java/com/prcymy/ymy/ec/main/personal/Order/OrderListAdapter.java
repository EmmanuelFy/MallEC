package com.prcymy.ymy.ec.main.personal.Order;

import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {

    protected OrderListAdapter(List<MultipleltemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleltemEntity entity) {
        super.convert(holder, entity);


    }
}
