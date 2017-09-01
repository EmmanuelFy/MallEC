package com.prcymy.ymy.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.EcBottomDelegate;
import com.prcymy.ymy.refresh.RefreshHandler;
import com.prcymy.ymy.ui.recycler.BaseDecoration;
import com.prcymy.ymy.utils.StartBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/5.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.tb_index)
    Toolbar tb_index = null;

    @BindView(R2.id.icon_index_scan)
    AppCompatImageView icon_index_scan = null;

    @BindView(R2.id.tv_search_view)
    AppCompatTextView tv_search_view = null;

    @BindView(R2.id.icon_index_message)
    AppCompatImageView icon_index_message = null;

    @BindView(R2.id.srl_index)
    RefreshLayout srl_index = null;

    @BindView(R2.id.rv_index)
    RecyclerView rv_index = null;


    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mRefreshHandler = RefreshHandler.create(srl_index,rv_index,new IndexDataConverter());

    }
    //初始化下拉刷新
    private void initRefreshLayout(){

    }

    //初始化recyclerview
    private void initRecyclerView(){

        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        rv_index.setLayoutManager(manager);
        rv_index.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.colorWhit),2));

        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        rv_index.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));


    }




    //懒加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index_data.json");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
