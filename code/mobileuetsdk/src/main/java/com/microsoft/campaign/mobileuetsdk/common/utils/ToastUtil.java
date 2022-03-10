package com.microsoft.campaign.mobileuetsdk.common.utils;

import android.content.Context;

/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class ToastUtil {
    public static void showShortToast(Context context, String text) {
        ToastInstance mToast = ToastInstance.getInstance(context);
        mToast.showShortToast(context, text);
    }

    public static void showShortToast(Context context, int strId) {
        ToastInstance.getInstance(context).showShortToast(context, strId);
    }

    public static void showShortToast(Context context, int resid, String str) {
        String message = context.getString(resid, str);
        ToastInstance.getInstance(context).showShortToast(context, message);
    }

    public static void showLongToast(Context context, String text) {
        ToastInstance.getInstance(context).showLongToast(context, text);
    }

    public static void showLongToast(Context context, int strId) {
        ToastInstance.getInstance(context).showLongToast(context, strId);
    }

    public static void showLongToast(Context context, int resid, String str) {
        String message = context.getString(resid, str);
        ToastInstance.getInstance(context).showLongToast(context, message);
    }
}
