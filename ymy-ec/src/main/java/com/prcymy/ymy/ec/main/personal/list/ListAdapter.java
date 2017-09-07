package com.prcymy.ymy.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
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
        addItemType(ItemType.ITEM_AVATAR,R.layout.arrow_item_avatar);
        addItemType(ItemType.ITEM_BOTTOM,R.layout.arrow_item_bottom);
        addItemType(ItemType.ITEM_SWITCH,R.layout.arrow_item_switch);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()){
            case ItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text,item.getmText());
                helper.setText(R.id.tv_arrow_value,item.getmValue());
                break;
            case ItemType.ITEM_AVATAR:
                Glide.with(mContext).load(item.getmImageUrl()).into((ImageView) helper.getView(R.id.img_arrow_avatar));

                break;
            case ItemType.ITEM_BOTTOM:
                helper.setText(R.id.tv_text,item.getmText());
                Glide.with(mContext).load(item.getmImageUrl()).into((ImageView) helper.getView(R.id.iv_phototv));
                break;

            case ItemType.ITEM_SWITCH:
                helper.setText(R.id.tv_arrow_switch_text,item.getmText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);

                //关闭消息推送
                switchCompat.setOnCheckedChangeListener(item.getmOnCheckedChangeListener());
                break;
            default:
                break;
        }
    }
}
