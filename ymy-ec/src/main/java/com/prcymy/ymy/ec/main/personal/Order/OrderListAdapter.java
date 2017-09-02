package com.prcymy.ymy.ec.main.personal.Order;

import android.support.v7.widget.AppCompatTextView;

import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {

    public OrderListAdapter(List<MultipleltemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleltemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String titleVal = entity.getField(MultipleFields.TITLE);
                final String priceVal = String.valueOf(entity.getField(OrderItemFields.PRICE));
                final String timeVal = entity.getField(OrderItemFields.TIME);

                title.setText(titleVal);
                price.setText("价格" + String.valueOf(priceVal));
                time.setText("时间" + timeVal);
                break;

            default:
                break;
        }
    }
}
