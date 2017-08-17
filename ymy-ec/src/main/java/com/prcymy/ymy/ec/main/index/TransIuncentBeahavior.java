package com.prcymy.ymy.ec.main.index;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ui.recycler.RgbValue;

/**
 * Created by Administrator on 2017/8/8.
 */

public class TransIuncentBeahavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;

    //定义颜色
    private static final RgbValue RGB_VALUE = RgbValue.create(255,124,2);

    public TransIuncentBeahavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;

    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        /*//增加滑动距离
        mDistanceY += dy;
        //toobar高度
        final int targetHeight = child.getBottom();

        //当滑动时并且距离小于toolbar高度的时候,调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targetHeight){
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int)alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }else if (mDistanceY > targetHeight){
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }*/
    }
}
