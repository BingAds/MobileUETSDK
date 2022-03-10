package com.microsoft.campaign.mobileuetsdk.common.utils;

import android.util.Log;

import com.microsoft.campaign.mobileuetsdk.conf.CommonConf;

public class LogCatUtil {

    //only for debuging turn it to true, when release turn it to false
    private static boolean SWITCH = CommonConf.SWITCH;

    public static void i(String tag, String msg){
        if (SWITCH){
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg){
        if (SWITCH){
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg){
        if (SWITCH){
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg){
        d(CommonConf.DEBUG_TAG, msg);
    }

    public static void e(String tag, String msg){
        if (SWITCH) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr){
        if (SWITCH){
            Log.w(tag, msg, tr);
        }
    }




}
