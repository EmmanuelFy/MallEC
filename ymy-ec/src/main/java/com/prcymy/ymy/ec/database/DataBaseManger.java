package com.prcymy.ymy.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/8/4.
 */

public class DataBaseManger {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DataBaseManger(){

    }

    public DataBaseManger init(Context context){
        initDao(context);

        return this;
    }

    private static class Holder{
     private static final DataBaseManger INSTANCE = new DataBaseManger();
    }

    public static DataBaseManger getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"mall_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }
}
