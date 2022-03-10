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
    private HashMap<String, String> extraParamMap;


    private Context context;

    public ParameterManager(){}

    public ParameterManager(Context context){
        this.context= context;
    }


    public int[] getScreenWidthHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return new int[]{screenWidth, screenHeight};
    }

    public HashMap<String, String> mergeParameter(HashMap<ParameterEnum, String> userextraParamMap){
        this.initExtraParam();
        //set default values, if user do not define
        for (ParameterEnum pe : ParameterEnum.values()){
            if (userextraParamMap.containsKey(pe)){
                continue;
            }
            userextraParamMap.put(pe, ParameterConf.DEFAULT);
        }
        //turn Emun into String for http request
        Iterator<Map.Entry<ParameterEnum, String>> iterator = userextraParamMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<ParameterEnum, String> entry = iterator.next();
            ParameterEnum ParameterEnum = entry.getKey();
            String value = entry.getValue();
            this.extraParamMap.put(ParameterEnum.getValue(), value);
        }
        return this.extraParamMap;
    }

    public HashMap<String, String> initExtraParam(){
        this.extraParamMap = new HashMap<>();
        int[] screenInfo = getScreenWidthHeight(this.context);
        TimeZone tz = TimeZone.getDefault();
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        this.extraParamMap.put(ParameterConf.DEVICE_MODEL, Build.MODEL);
        this.extraParamMap.put(ParameterConf.BRAND, Build.BRAND);
        this.extraParamMap.put(ParameterConf.CPU_ABI, Build.CPU_ABI);
        this.extraParamMap.put(ParameterConf.DISPLAY, Build.DISPLAY);
        this.extraParamMap.put(ParameterConf.DEVICE, Build.DEVICE);
        this.extraParamMap.put(ParameterConf.HOST, Build.HOST);
        this.extraParamMap.put(ParameterConf.DEVICE_OS_VERSION, Build.VERSION.RELEASE);
        this.extraParamMap.put(ParameterConf.HARDWARE, Build.HARDWARE);
        this.extraParamMap.put(ParameterConf.SCREEN_WIDTH, screenInfo[0] + ValueConf.STR_DEFAULT_VALUE);
        this.extraParamMap.put(ParameterConf.SCREEN_HEIGHT, screenInfo[1] + ValueConf.STR_DEFAULT_VALUE);
        this.extraParamMap.put(ParameterConf.TIME_ZONE, tz.getID());
        this.extraParamMap.put(ParameterConf.LANGUAGE, language);
        this.extraParamMap.put(ParameterConf.COUNTRY, country);
        this.extraParamMap.put(ParameterConf.VERSION_SDK, Build.VERSION.SDK_INT + ValueConf.STR_DEFAULT_VALUE);
        this.extraParamMap.put(ParameterConf.MOBILE_UET_SDK_VERSION, ValueConf.MOBILE_UET_SDK_VERSION);

        return this.extraParamMap;

    }

}
