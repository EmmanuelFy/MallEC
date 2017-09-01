package com.prcymy.ymy.ec.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.prcymy.ymy.delegates.MallDelegate;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private MallDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListBean(int mItemType, String mImageUrl, String mText, String mValue, int mId, MallDelegate mDelegate, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public int getmItemType() {
        return mItemType;
    }

    public void setmItemType(int mItemType) {
        this.mItemType = mItemType;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmText() {
        if (mText == null){
            return "";
        }
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public MallDelegate getmDelegate() {
        return mDelegate;
    }

    public void setmDelegate(MallDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setmOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }


    @Override
    public int getItemType() {
        return mItemType;

    }
    public static final class  Builder{
        private int id = 0;
        private int itemTyoe = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private MallDelegate delegate = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemTyoe(int itemTyoe) {
            this.itemTyoe = itemTyoe;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setDelegate(MallDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build(){
            return new ListBean(itemTyoe,
                    imageUrl,
                    text,
                    value,
                    id,
                    delegate,onCheckedChangeListener);
        }
    }
}
