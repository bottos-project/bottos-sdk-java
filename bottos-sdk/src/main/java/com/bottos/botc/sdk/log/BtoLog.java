package com.bottos.botc.sdk.log;

import android.util.Log;

import com.bottos.botc.sdk.Config.Config;

/**
 * Created by xionglh on 2018/9/4
 */
public class BtoLog {

    public static void v(String tag, String msg) {
        if (Config.DEBUG_MODE)
            Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (Config.DEBUG_MODE)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (Config.DEBUG_MODE)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (Config.DEBUG_MODE)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (Config.DEBUG_MODE)
            Log.e(tag, msg);
    }
}
