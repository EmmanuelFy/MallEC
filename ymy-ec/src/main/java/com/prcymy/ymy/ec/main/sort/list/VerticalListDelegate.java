package com.prcymy.ymy.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.sort.SortDelegate;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.IFail;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;
import com.prcymy.ymy.utils.Http;

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
                .url(Http.API_CATEGORY)
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

                .fail(new IFail() {
                    @Override
                    public void onFail() {
                        Logger.d("onFail");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Logger.d("onError",msg);
                    }
                })
                .build()
                .get();
    }
}
