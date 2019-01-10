package cn.n0texpecterr0r.timemovie;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cn.n0texpecterr0r.timemovie.base.image.ImageLoader;
import cn.n0texpecterr0r.timemovie.base.image.engines.GlideEngine;
import cn.n0texpecterr0r.timemovie.db.DaoMaster;
import cn.n0texpecterr0r.timemovie.db.DaoSession;

/**
 * 时光电影全局的Application
 *
 * @author N0tExpectErr0r
 * @time 2019/01/09
 */
public class TimeApplication extends Application {
    private static Context sContext;
    private boolean isDebug = true;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if(sContext == null)
            sContext = getApplicationContext();
        initImageLoader();
        initGreenDao();
    }

    private void initImageLoader() {
        // 使用Glide引擎
        ImageLoader.getInstance().setEngine(new GlideEngine());
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "timeMovie.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }


    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static Context getContext() {
        return sContext;
    }
}
