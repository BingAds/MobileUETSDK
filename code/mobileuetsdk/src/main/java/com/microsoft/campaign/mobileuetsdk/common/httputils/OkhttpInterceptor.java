package com.microsoft.campaign.mobileuetsdk.common.httputils;

import com.microsoft.campaign.mobileuetsdk.common.utils.LogCatUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import okhttp3.*;

public class OkhttpInterceptor implements Interceptor {

    private int maxTry;

    public OkhttpInterceptor(int maxTry){
        this.maxTry=maxTry;
    }

    public Response intercept(@NotNull Chain chain) throws IOException {
        return tryProceed(chain,0);
    }

    Response tryProceed(Chain chain,int tryTimes){
        Boolean tryFlag = true;
        Response response =  null;
        while(tryFlag && tryTimes < maxTry)
        {
            Request request = chain.request();
            try{
                LogCatUtil.i("[HTTP-INFO]", "try " + (tryTimes+1) + " times for " + request.url());
                response = chain.proceed(request);
                tryFlag = false;
            }catch (Exception e){
                LogCatUtil.e("[HTTP-ERROR]", "try " + (tryTimes+1) + " times exception: " + e.getMessage());
                tryTimes ++;
                tryFlag = true;
            }
        }

        LogCatUtil.i("[HTTP-INFO]", "opt out of retry");
        return response;
    }
}
