package com.prcymy.ymy.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prcymy.ymy.delegates.bottom.BottomItemDelegate;
import com.prcymy.ymy.delegates.web.WebDelegateImpl;
import com.prcymy.ymy.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Administrator on 2017/8/10.
 */

public class DiscoverDelegate extends BottomItemDelegate {



    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("first.html");
        //delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discover_contianer,delegate);
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
