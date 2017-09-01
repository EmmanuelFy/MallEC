package com.prcymy.ymy.ec.main.personal.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.personal.Order.allorder.AllOrderDelegate;
import com.prcymy.ymy.ec.main.personal.Order.kek.FaHuo;
import com.prcymy.ymy.ec.main.personal.PersonalDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/1.
 */

public class OrderDelegate extends MallDelegate {

    private PersonalDelegate delegate = null;

    //标题
    private List<String> title = new ArrayList<>();

    private List<Fragment> fragments = new ArrayList<>();

    private int position;

    public OrderDelegate(int position) {
        this.position = position;
    }

    @BindView(R2.id.tool_bar)
    Toolbar toolbar = null;

    @BindView(R2.id.order_all_tab)
    TabLayout tab = null;

    @BindView(R2.id.order_all_pager)
    ViewPager pager = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }

    private void initView() {



        title.add("全部");
        title.add("待付款");

        fragments.add(new AllOrderDelegate());
        fragments.add(new FaHuo());

        pager.setAdapter(new FragmentAdapter(getChildFragmentManager(),title,fragments));
        tab.setupWithViewPager(pager);

        tab.getTabAt(position).select();
        pager.setCurrentItem(position);

    }
}
