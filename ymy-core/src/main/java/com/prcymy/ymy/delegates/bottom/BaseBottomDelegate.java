package com.prcymy.ymy.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.prcymy.ymy.R;
import com.prcymy.ymy.R2;
import com.prcymy.ymy.delegates.MallDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/8/5.
 */

public abstract class BaseBottomDelegate extends MallDelegate implements View.OnClickListener {

    private final ArrayList<BottomBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATE = new ArrayList<>();

    private final LinkedHashMap<BottomBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;

    private int mIndexDelegate = 0;

    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottombar = null;


    public abstract LinkedHashMap<BottomBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate();

        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();

        final LinkedHashMap<BottomBean, BottomItemDelegate> items = setItems(builder);

        ITEMS.putAll(items);

        for (Map.Entry<BottomBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATE.add(value);
        }
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottombar);

            final RelativeLayout item = (RelativeLayout) mBottombar.getChildAt(i);

            //设置每个的点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomBean bean = TAB_BEANS.get(i);

            //初始化数据
            itemIcon.setImageResource(bean.getICON());
            itemTitle.setText(bean.getTITLE());
            if (i == mIndexDelegate) {

                itemIcon.setImageResource(R.drawable.mainpage_home_pressed_ic);
                itemTitle.setTextColor(mClickedColor);
            }

        }
        final SupportFragment[] delegateArray = ITEM_DELEGATE.toArray(new SupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    //恢复默认

    private void resetColor() {
        final int count = mBottombar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottombar.getChildAt(i);
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            //判断添加默认图片
            /***判断tag
             * itemIcon.setImageResource(R.drawable.fragmentation_help);
             */
            if (i == 0){

                itemIcon.setImageResource(R.drawable.mainpage_home_nor_ic);
            }

            if (i== 1){
                itemIcon.setImageResource(R.drawable.mainpage_category_nor_ic);
            }

            if (i== 2){
                itemIcon.setImageResource(R.drawable.mainpage_topic_nor_ic);
            }

            if (i == 3){
                itemIcon.setImageResource(R.drawable.all_shopping_cart_nor_ic);

            }

            if (i== 4){
                itemIcon.setImageResource(R.drawable.mainpage_person_nor_ic);
            }

            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);

        //判断当前点击后图片
        /***判断tag
         * itemIcon.setImageResource(R.drawable.fragmentation_help);
         */
        if (tag == 0){


            itemIcon.setImageResource(R.drawable.mainpage_home_pressed_ic);
        }

        if (tag== 1){

            itemIcon.setImageResource(R.drawable.mainpage_category_pressed_ic);

        }

        if (tag == 2){
            itemIcon.setImageResource(R.drawable.mainpage_topic_pressed_ic);
        }

        if (tag == 3){
            itemIcon.setImageResource(R.drawable.all_shopping_cart_pressed_ic);
        }

        if (tag == 4){

            itemIcon.setImageResource(R.drawable.mainpage_person_pressed_ic);
        }

        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);

        showHideFragment(ITEM_DELEGATE.get(tag), ITEM_DELEGATE.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;

    }
}
