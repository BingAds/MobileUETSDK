package com.microsoft.campaign.mobileuetsdk.common.httputils;

import com.microsoft.campaign.mobileuetsdk.common.utils.LogCatUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.*;


/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class OkHttp {

    //this is a Okhttp client to send the log request.
    public static void get(String url) {
        final boolean[] exceptionFlag = {false};
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1500, TimeUnit.SECONDS)
                .addInterceptor(new OkhttpInterceptor(3))
                .retryOnConnectionFailure(false)
                .build();
        Request request = new Request.Builder()
                //you can add whatever Header here by below sample.
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie",    "add cookies here")
                //below is add the user-agent.
                .addHeader("user-agent", "Microsoft UET SDK client v1.0 (Android)").
                        url(url).get().build();

              //okHttpClient.newCall(request).execute();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    exceptionFlag[0] = true;
                    LogCatUtil.e("[HTTP-ERROR]", "Failed to send the request: [" + url +"]" );
                    LogCatUtil.e("[HTTP-ERROR]", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    LogCatUtil.i("[HTTP-INFO]", "RESPONSE status code: " + response.code());
                }
            });

        if(!exceptionFlag[0]) LogCatUtil.i("[HTTP-INFO]", "send the request: [" + url +"] successfully." );
    }
}