package com.prcymy.ymy.refresh;

import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.DataConverter;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by Administrator on 2017/8/8.
 */

public class RefreshHandler implements OnRefreshListener,OnLoadmoreListener {

    private final RefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLREVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHandler(RefreshLayout refreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean){
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLREVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        REFRESH_LAYOUT.setOnLoadmoreListener(this);
    }

    public static RefreshHandler create(RefreshLayout refreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter){
        return new RefreshHandler(refreshLayout,recyclerView,converter,new PagingBean());
    }

    private void refresh(){

    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total")).setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        RECYCLREVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }
    //下拉刷新
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {


        refreshlayout.finishRefresh();
    }


    //上拉加载
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

        refreshlayout.finishLoadmore();
    }
}
