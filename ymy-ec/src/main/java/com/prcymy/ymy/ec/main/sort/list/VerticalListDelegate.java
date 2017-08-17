package com.prcymy.ymy.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.sort.SortDelegate;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/9.
 * 左侧垂直列表
 */

public class VerticalListDelegate extends MallDelegate{

    @BindView(R2.id.rv_vertical_meu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRcyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画
        mRecyclerView.setItemAnimator(null);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        initRcyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleltemEntity> data
                                = new VerticalListDataConverter()
                                .setJsonData(response)
                                .convert();
                        final SortDelegate delegate = getParentDelegate();
                         final  SortRecyclerAdapter adapter
                                 = new SortRecyclerAdapter(data,delegate);

                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
