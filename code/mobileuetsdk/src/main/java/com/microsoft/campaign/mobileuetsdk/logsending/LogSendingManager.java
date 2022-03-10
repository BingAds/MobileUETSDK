package com.microsoft.campaign.mobileuetsdk.logsending;

import android.content.Context;
import android.os.Build;

import com.microsoft.campaign.mobileuetsdk.common.httputils.OkHttp;
import com.microsoft.campaign.mobileuetsdk.common.utils.CacheUtil;
import com.microsoft.campaign.mobileuetsdk.common.utils.DeviceUtil;
import com.microsoft.campaign.mobileuetsdk.common.utils.LogCatUtil;
import com.microsoft.campaign.mobileuetsdk.common.utils.StringUtil;
import com.microsoft.campaign.mobileuetsdk.conf.CommonConf;
import com.microsoft.campaign.mobileuetsdk.conf.ParameterConf;
import com.microsoft.campaign.mobileuetsdk.conf.ValueConf;
import com.microsoft.campaign.mobileuetsdk.parameter.ParameterManager;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class LogSendingManager {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LogSendingManager(){}

    public LogSendingManager(Context context){
        this.context = context;
    }

    public void setUserWebId(String webUserId){
        if (!StringUtil.isEmpty(webUserId)){
            CacheUtil.KVCache.put(ParameterConf.WEB_USER_ID, webUserId);
        }
    }

    public void sendEvent(Map<String, String> requiredParam, Map<String, String> optionalParam) {
        this.firstCallLogic();
        String paramCheckResult = checkRequiredParam(requiredParam);
        if(!paramCheckResult.equals(ParameterConf.PARAM_VALID))
        {
            LogCatUtil.e(CommonConf.DEBUG_TAG, paramCheckResult);
            return ;
        }

        fillRequiredParam(requiredParam);

        if(null == optionalParam) optionalParam = new HashMap<>();
        fillOptionalParam(optionalParam);

        //turn map to requestParams String
        String requestParams = StringUtil.buildRequestParam(requiredParam, optionalParam);
        final String fullUrl = CommonConf.URL + ValueConf.QUESTION_MARK + requestParams;
        LogCatUtil.debug(fullUrl);
        //sent request to G-server
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttp.get(fullUrl);
            }
        }).start();

        LogCatUtil.debug(requestParams);
    }

    public String checkRequiredParam(Map<String, String> requiredParam)
    {
        if(null == requiredParam) return  ParameterConf.PARAM_NULL;
        if(!requiredParam.containsKey(ParameterConf.TAG_ID) || !requiredParam.containsKey(ParameterConf.EVENT_ACTION)|| !requiredParam.containsKey(ParameterConf.APP_ID))
            return ParameterConf.PARAM_INVALID;

        String paramTiValue = requiredParam.get(ParameterConf.TAG_ID);
        String paramEaValue = requiredParam.get(ParameterConf.EVENT_ACTION);
        String paramAppIDValue = requiredParam.get(ParameterConf.APP_ID);
        if(null == paramTiValue || null == paramEaValue || null == paramAppIDValue) return ParameterConf.PARAM_INVALID;
        if(paramTiValue.equals("") || paramEaValue.equals("") || paramAppIDValue.equals("")) return ParameterConf.PARAM_INVALID;
        return ParameterConf.PARAM_VALID;
    }

    // add required background param
    public void fillRequiredParam(Map<String, String> requiredParam)
    {
        requiredParam.remove(ParameterConf.MOBILE_USER_ID);
        requiredParam.put(ParameterConf.MOBILE_USER_ID, CacheUtil.KVCache.get(ParameterConf.ANDROID_ID));

        requiredParam.remove(ParameterConf.UET_VERSION);
        requiredParam.put(ParameterConf.UET_VERSION, ParameterConf.UET_VERSION_VALUE);

        requiredParam.remove(ParameterConf.SESSION_ID);
        requiredParam.put(ParameterConf.SESSION_ID, CacheUtil.SESSION_ID);

        requiredParam.remove(ParameterConf.EVENT_TYPE);
        requiredParam.put(ParameterConf.EVENT_TYPE, ParameterConf.EVENT_TYPE_CUSTOM);

        requiredParam.remove(ParameterConf.RANDOM_NUMBER);
        requiredParam.put(ParameterConf.RANDOM_NUMBER, (int)(Math.ceil(Math.random() * ValueConf.RANDOM_TIME) + ValueConf.RANDOM_TIME) + ValueConf.STR_DEFAULT_VALUE);
    }

    // add optional background param
    public void fillOptionalParam(Map<String, String> optionalParam)
    {
        optionalParam.remove(ParameterConf.DEVICE_UUID);
        optionalParam.put(ParameterConf.DEVICE_UUID, CacheUtil.KVCache.get(ParameterConf.DEVICE_UUID));

        ParameterManager parameterManager = new ParameterManager(this.context);
        HashMap<String, String> backgroundParamMap = parameterManager.initExtraParam();
        for(String key:backgroundParamMap.keySet())
        {
            optionalParam.remove(key);
            optionalParam.put(key, backgroundParamMap.get(key));
        }

        //set other params
        optionalParam.put(ParameterConf.PACKAGE_NAME, DeviceUtil.getPackageName(this.context));
        optionalParam.put(ParameterConf.APP_NAME, DeviceUtil.getAppName(this.context));
        optionalParam.put(ParameterConf.APP_VERSION, DeviceUtil.getVersionName(this.context));
        optionalParam.put(ParameterConf.DEVICE_OS, ValueConf.OS);
        optionalParam.put(ParameterConf.SEND_TIME, System.currentTimeMillis() + ValueConf.STR_DEFAULT_VALUE);
        optionalParam.put(ParameterConf.NETWORK_OPERATOR_NAME, CacheUtil.KVCache.get(ParameterConf.NETWORK_OPERATOR_NAME));
        //optionalParam.put(ParameterConf.WEB_USER_ID, CacheUtil.KVCache.get(ParameterConf.WEB_USER_ID));
    }

    private void firstCallLogic(){
        //create session id and initialize some device's info at first call
        if ("".equals(CacheUtil.SESSION_ID)){
            String currentTimeForSession = System.currentTimeMillis() + ValueConf.STR_DEFAULT_VALUE;
            String randomStrForSession = (int)(Math.ceil(Math.random() * ValueConf.RANDOM_TIME) +
                    ValueConf.RANDOM_TIME) + ValueConf.STR_DEFAULT_VALUE;
            CacheUtil.SESSION_ID = StringUtil.md5(currentTimeForSession + randomStrForSession);
            //initialize some device's info
            CacheUtil.KVCache.put(ParameterConf.ANDROID_ID, DeviceUtil.getAndroidId(this.context));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CacheUtil.KVCache.put(ParameterConf.NETWORK_OPERATOR_NAME, DeviceUtil.getOperatorName(this.context));
                CacheUtil.KVCache.put(ParameterConf.DEVICE_UUID, DeviceUtil.getUid(this.context));
            } else {
                CacheUtil.KVCache.put(ParameterConf.NETWORK_OPERATOR_NAME, ValueConf.STR_DEFAULT_VALUE);
                CacheUtil.KVCache.put(ParameterConf.DEVICE_UUID, ValueConf.STR_DEFAULT_VALUE);
            }
        }
    }
}
