package com.microsoft.campaign.mobileuetsdk.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.Sampler;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.microsoft.campaign.mobileuetsdk.R;
import com.microsoft.campaign.mobileuetsdk.conf.ValueConf;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class DeviceUtil {

    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }


    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getModel() {
        return android.os.Build.MODEL;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static String getDeviceBoand() {
        return Build.BOARD;
    }

    public static String getHOST() {
        return Build.HOST;
    }


    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static synchronized String getAppName(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            int labelRes = packageInfo.applicationInfo.labelRes;

            return context.getResources().getString(labelRes);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "";

    }

    public static synchronized String getVersionName(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            return packageInfo.versionName;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "";

    }

    public static synchronized int getVersionCode(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            return packageInfo.versionCode;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    public static synchronized String getPackageName(Context context) {

        try {

            PackageManager packageManager = context.getPackageManager();

            PackageInfo packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);

            return packageInfo.packageName;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ValueConf.STR_DEFAULT_VALUE;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static synchronized String getUid(Context context) {
//        getPermission(context);
        String uid = ValueConf.STR_DEFAULT_VALUE;

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return uid;
        }
        uid = tm.getDeviceId();

        //String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);

        uid = StringUtil.md5(uid);
        return uid;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static synchronized String getOperatorName(Context context) {
//        getPermission(context);
        String operatorName = ValueConf.STR_DEFAULT_VALUE;

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return operatorName;
        }
        operatorName = tm.getNetworkOperatorName();
        System.out.println(operatorName);
        return operatorName;

    }

    public static synchronized String getAndroidId(Context context) {
        String androidId = ValueConf.STR_DEFAULT_VALUE;
        androidId = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        androidId = StringUtil.md5(androidId);
        return androidId;

    }

}
