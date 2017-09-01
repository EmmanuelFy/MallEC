package com.prcymy.ymy.ec.main.personal.Order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, List<String> title, List<Fragment> fragments) {
        super(fm);
        this.title = title;
        this.fragments = fragments;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
