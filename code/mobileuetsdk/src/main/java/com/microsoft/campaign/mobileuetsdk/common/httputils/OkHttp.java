package com.microsoft.campaign.mobileuetsdk.common.httputils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class OkHttp {

    //this is a Okhttp client to send the log request.
    public static String get(String url) {
        String rst = "";
        OkHttpClient okHttpClient = new OkHttpClient();
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
        try {
            Response response = okHttpClient.newCall(request).execute();
            rst = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rst;
    }


}