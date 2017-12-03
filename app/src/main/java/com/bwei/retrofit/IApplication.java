package com.bwei.retrofit;

import android.app.Application;


import com.bwei.retrofit.dao.DaoMaster;
import com.bwei.retrofit.dao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by muhanxi on 17/12/1.
 */

public class IApplication extends Application {

    public static IGetDataBase iGetDataBase;
    public static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();


        Fresco.initialize(this);
        Retrofit retrofit =  new Retrofit.Builder().baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iGetDataBase = retrofit.create(IGetDataBase.class);


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"test");

        Database database =  helper.getWritableDb();


        session = new DaoMaster(database).newSession();

    }
}
