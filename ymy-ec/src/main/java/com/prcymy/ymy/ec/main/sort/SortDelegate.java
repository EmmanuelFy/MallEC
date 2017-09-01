package com.prcymy.ymy.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.main.sort.content.ContentDelegate;
import com.prcymy.ymy.ec.main.sort.list.VerticalListDelegate;
import com.prcymy.ymy.utils.StartBar;

/**
 * Created by Administrator on 2017/8/5.
 */

public class SortDelegate extends BottomItemDelegate {



    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    //懒加载  可见时加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        final VerticalListDelegate delegate = new VerticalListDelegate();
        //设置左侧列表
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,delegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));

    }
}
