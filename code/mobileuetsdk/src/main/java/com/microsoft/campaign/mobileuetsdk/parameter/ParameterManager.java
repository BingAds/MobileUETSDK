package com.microsoft.campaign.mobileuetsdk.parameter;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import com.microsoft.campaign.mobileuetsdk.conf.ParameterConf;
import com.microsoft.campaign.mobileuetsdk.conf.ParameterEnum;
import com.microsoft.campaign.mobileuetsdk.conf.ValueConf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class ParameterManager {

    //load device information
    private Context context;

    public ParameterManager(Context context){
        this.context= context;
    }

    public int[] getScreenWidthHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return new int[]{screenWidth, screenHeight};
    }

    public HashMap<String, String> initExtraParam(){
        HashMap<String, String> extraParamMap = new HashMap<>();
        int[] screenInfo = getScreenWidthHeight(this.context);
        TimeZone tz = TimeZone.getDefault();
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        extraParamMap.put(ParameterConf.DEVICE_MODEL, Build.MODEL);
        extraParamMap.put(ParameterConf.BRAND, Build.BRAND);
        extraParamMap.put(ParameterConf.CPU_ABI, Build.CPU_ABI);
        extraParamMap.put(ParameterConf.DISPLAY, Build.DISPLAY);
        extraParamMap.put(ParameterConf.DEVICE, Build.DEVICE);
        extraParamMap.put(ParameterConf.HOST, Build.HOST);
        extraParamMap.put(ParameterConf.DEVICE_OS_VERSION, Build.VERSION.RELEASE);
        extraParamMap.put(ParameterConf.HARDWARE, Build.HARDWARE);
        extraParamMap.put(ParameterConf.SCREEN_WIDTH, screenInfo[0] + ValueConf.STR_DEFAULT_VALUE);
        extraParamMap.put(ParameterConf.SCREEN_HEIGHT, screenInfo[1] + ValueConf.STR_DEFAULT_VALUE);
        extraParamMap.put(ParameterConf.TIME_ZONE, tz.getID());
        extraParamMap.put(ParameterConf.LANGUAGE, language);
        extraParamMap.put(ParameterConf.COUNTRY, country);
        extraParamMap.put(ParameterConf.VERSION_SDK, Build.VERSION.SDK_INT + ValueConf.STR_DEFAULT_VALUE);
        extraParamMap.put(ParameterConf.MOBILE_UET_SDK_VERSION, ValueConf.MOBILE_UET_SDK_VERSION);
        return extraParamMap;
    }

}
