package com.prcymy.ymy.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.main.personal.list.ListBean;

/**
 * Created by Administrator on 2017/9/6.
 */

public class PersnoalOnClickListener extends SimpleClickListener{

    private MallDelegate DELEGATE;

    public PersnoalOnClickListener(MallDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getmId();
        switch (id){
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            case 2:

                break;

            default:

                break;
        }
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
