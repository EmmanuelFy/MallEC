package com.prcymy.ymy.ui.recycler;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/8.
 */

public abstract class DataConverter {

    protected  final ArrayList<MultipleltemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    //返回Entity数组
    public abstract ArrayList<MultipleltemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    protected  String getJsonData(){
        if (mJsonData == null || mJsonData.isEmpty()){
            throw  new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
