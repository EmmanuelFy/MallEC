package com.prcymy.ymy.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.main.personal.list.ListBean;

/**
 * Created by Administrator on 2017/9/7.
 */

public class SettingsClickListener extends SimpleClickListener {

    private MallDelegate DELEGATE;

    public SettingsClickListener(MallDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        final ListBean ben = (ListBean) baseQuickAdapter.getData().get(position);
        int id = ben.getmId();
        switch (id){
            case 1:
                //这是消息推送的开关
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(ben.getmDelegate());
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
