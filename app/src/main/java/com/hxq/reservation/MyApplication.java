package com.hxq.reservation;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by wnw on 2018/5/12.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
