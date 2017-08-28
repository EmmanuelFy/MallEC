package com.prcymy.ymy.ec.main.index;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.main.detail.GoodsDetailDelegate;

/**
 * Created by Administrator on 2017/8/9.
 */

public class IndexItemClickListener extends SimpleClickListener{

    private final MallDelegate DELEGATE;

    private IndexItemClickListener(MallDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(MallDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {



        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.creat();
        DELEGATE.start(detailDelegate);
        Log.i("----------",view.getId()+"======"+position);

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
