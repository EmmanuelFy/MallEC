package com.prcymy.ymy.ec.main.sort.content;

import android.support.v7.widget.AppCompatTextView;

import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.main.sort.SortDelegate;
import com.prcymy.ymy.ui.recycler.ItemType;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class AdapterCC extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;

    protected AdapterCC(List<MultipleltemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        addItemType(ItemType.RIGHT_MENU_LIST,R.layout.item_section_header);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleltemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()){
            case ItemType.RIGHT_MENU_LIST:
                final AppCompatTextView textView = holder.getView(R.id.header);
                String s = entity.getField(MultipleFields.TEXT);
                textView.setText(s);
                break;
        }
    }
}
