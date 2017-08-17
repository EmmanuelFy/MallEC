package com.prcymy.ymy.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Administrator on 2017/8/8.
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder>{

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
