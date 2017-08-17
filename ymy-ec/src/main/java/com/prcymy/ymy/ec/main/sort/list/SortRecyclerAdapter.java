package com.prcymy.ymy.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.main.sort.SortDelegate;
import com.prcymy.ymy.ec.main.sort.content.ContentDelegate;
import com.prcymy.ymy.ui.recycler.ItemType;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleltemEntity> data,SortDelegate delegate) {
        super(data);

        this.DELEGATE = delegate;

        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleltemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;

                //点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                         if (mPrePosition != currentPosition){
                             //还原上一个
                             getData().get(mPrePosition).setField(MultipleFields.TAG,false);
                             notifyItemChanged(mPrePosition);

                             //更新选择的Item
                             entity.setField(MultipleFields.TAG,true);
                             notifyItemChanged(currentPosition);
                             mPrePosition = currentPosition;

                             final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);

                             showContent(contentId);
                         }

                    }
                });

                if (!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_cheat));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));


                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name,text);

                break;
            default:
                break;
        }
    }

    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate){
        final MallDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);

        if (contentDelegate != null){
            contentDelegate.replaceFragment(delegate,false);
        }
    }
}
