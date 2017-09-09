package com.prcymy.ymy.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.sort.SortDelegate;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.IError;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;
import com.prcymy.ymy.utils.Http;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/9.
 * 右侧布局
 */

public class ContentDelegate extends MallDelegate {

    //id标示进入哪一个
    private static final String ARG_CONTENT_ID = "CONTENT_ID";

    //类变量
    private int mContentId = -1;

    private List<SectionBean> mData;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args!= null){
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId){
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    private void intiData(){
        RestClient.builder()
                .url(Http.API_CATEGORY_INFO+"/"+mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        /*mData = new SectionDataConverter().converter(response);
                        final  SectionAdapter sectionAdapter
                                = new SectionAdapter(R.layout.item_section_content,
                                R.layout.item_section_header,mData);*/
                        final List<MultipleltemEntity> data = new Section().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final AdapterCC adapterCC = new AdapterCC(data,delegate);
                        mRecyclerView.setAdapter(adapterCC);
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

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //初始化布局
        final StaggeredGridLayoutManager manager
                = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        intiData();
    }

}
