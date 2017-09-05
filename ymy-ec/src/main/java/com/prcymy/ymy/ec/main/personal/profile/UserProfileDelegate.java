package com.prcymy.ymy.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.R2;
import com.prcymy.ymy.ec.main.personal.list.ItemType;
import com.prcymy.ymy.ec.main.personal.list.ListAdapter;
import com.prcymy.ymy.ec.main.personal.list.ListBean;
import com.prcymy.ymy.ec.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UserProfileDelegate extends MallDelegate{

    @BindView(R2.id.rv_user_profile)
    RecyclerView mUserRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504519226768&di=48b85911c447c72c4acd5881a1066a80&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F120803%2F1-120P3102145.jpg")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setValue("哈哈")
                .setDelegate(new NameDelegate())
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置")
                .build();

        final ListBean age = new ListBean.Builder()
                .setItemTyoe(ItemType.ITEM_NORMAL)
                .setId(4)
                .setText("年龄")
                .setValue("未设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(age);

        final LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mUserRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mUserRecyclerView.setAdapter(adapter);
        mUserRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
