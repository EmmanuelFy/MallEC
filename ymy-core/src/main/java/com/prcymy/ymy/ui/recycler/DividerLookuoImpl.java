package com.prcymy.ymy.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by Administrator on 2017/8/8.
 */

public class DividerLookuoImpl implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookuoImpl(int color ,int size) {
        this.COLOR = color;
        this.SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .build();
    }
}
