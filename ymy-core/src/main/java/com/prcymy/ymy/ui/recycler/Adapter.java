package com.prcymy.ymy.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class Adapter extends BaseMultiItemQuickAdapter<MultipleltemEntity,MultipleViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public Adapter(List<MultipleltemEntity> data) {
        super(data);
        init();
    }

    private void init() {


    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleltemEntity item) {

    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }
}
