package com.prcymy.ymy.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.prcymy.ymy.ec.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ListAdapter(List<ListBean> data) {
        super(data);

        addItemType(ItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()){
            case 20:
                helper.setText(R.id.tv_arrow_text,item.getmText());
                helper.setText(R.id.tv_arrow_value,item.getmValue());
                break;
            default:
                break;
        }
    }
}
