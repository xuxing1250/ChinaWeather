package com.example.administrator.chinaweather;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/7/10.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
    }

    public static Context getmContext(){
        return mContext;
    }
}
