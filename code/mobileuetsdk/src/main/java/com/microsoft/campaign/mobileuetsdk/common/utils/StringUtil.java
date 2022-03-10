package com.microsoft.campaign.mobileuetsdk.common.utils;

import com.microsoft.campaign.mobileuetsdk.conf.CommonConf;
import com.microsoft.campaign.mobileuetsdk.conf.ParameterConf;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class StringUtil {
    public static String mapToJsonString(HashMap<String, Object> map){
        String jsonStr = "";
        JSONObject jsonObject = new JSONObject(map);
        if (null != jsonObject){
            jsonStr = jsonObject.toString();
        }
        return jsonStr;
    }

    public static boolean isEmpty(String value){
        boolean rst = false;
        if (null == value || "".equals(rst)){
            rst = true;
        }
        return rst;
    }

    public static String md5(String string) {
        if (isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String buildRequestParam(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        int mapsize = map.size();
        if (mapsize > 0) {
            int index = 0;
            for (String key : map.keySet()) {
                index ++;
                sb.append(key + "=");
                if (index == mapsize){
                    String value = map.get(key);
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    sb.append(value);

                }else{
                    if (isEmpty(map.get(key))) {
                        sb.append("&");
                    } else {
                        String value = map.get(key);
                        try {
                            value = URLEncoder.encode(value, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        sb.append(value + "&");
                    }
                }

            }
        }
        return sb.toString();
    }

    public static String buildRequestParam(Map<String, String> requiredParam, Map<String, String> optionalParam) {
        StringBuilder sb = new StringBuilder();

        sb.append(ParameterConf.TAG_ID + "=" + requiredParam.get(ParameterConf.TAG_ID) + "&");
        sb.append(ParameterConf.UET_VERSION + "=" + requiredParam.get(ParameterConf.UET_VERSION) + "&");

        for (String key : requiredParam.keySet()) {
            if(!key.equals(ParameterConf.RANDOM_NUMBER) && !key.equals(ParameterConf.TAG_ID) && !key.equals(ParameterConf.UET_VERSION))
            {
                sb.append(key + "=");
                sb.append(getUrlCoderString(requiredParam.get(key)) + "&");
            }
        }

        for (String key : optionalParam.keySet()) {
                sb.append(key + "=");
                String value = optionalParam.get(key);
                if(null != value)
                {
                    if(value.contains(",")) sb.append(getUrlCoderString(buildArrayStr(value)) + "&");
                    else  sb.append(getUrlCoderString(value) + "&");
                }else{
                    sb.append( "&");
                }

        }

        // add the random number at the end as a cache buster
        sb.append(ParameterConf.RANDOM_NUMBER + "=" + requiredParam.get(ParameterConf.RANDOM_NUMBER));
        return sb.toString();
    }

    public static String getUrlCoderString(String pString)
    {
        String result ="";
        try {
            result = URLEncoder.encode(pString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogCatUtil.e(CommonConf.DEBUG_TAG, e.toString());
            e.printStackTrace();
        }
        return result;
    }

    public static String buildArrayStr(String arrayString)
    {
        StringBuilder sb = new StringBuilder();
        String[] ary = arrayString.split(",");
        for(int i=0; i < ary.length; i++)
        {
            if(!ary[i].contains("&")) sb.append(getUrlCoderString(ary[i]));
            else{
                StringBuilder ssb = new StringBuilder();
                String[] sAry = ary[i].split("&");
                for(int j=0; j < sAry.length; j++)
                {
                    int ind = sAry[j].lastIndexOf("=");
                    String name = sAry[j].substring(0, ind);
                    String value = sAry[j].substring(ind + 1);
                    ssb.append(getUrlCoderString(name));
                    ssb.append("=");
                    ssb.append(getUrlCoderString(value));
                    if(j != sAry.length - 1) ssb.append("&");
                }
                sb.append(ssb.toString());
            }
            if( i != ary.length - 1) sb.append(",");
        }
        return sb.toString();
    }
}
