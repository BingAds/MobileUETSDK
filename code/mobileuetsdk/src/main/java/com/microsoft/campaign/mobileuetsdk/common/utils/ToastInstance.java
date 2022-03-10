package com.microsoft.campaign.mobileuetsdk.common.utils;

import android.content.ContentProvider;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public class ToastInstance {
    private static int Y_OFFSET = 0;
    private static ToastInstance instance = null;
    private Context context;
    private Toast toast;
    private Toast longToast;
    private ToastInstance(Context contex){
        this.context = contex;
    }
    public static ToastInstance getInstance(Context context){
        if (instance == null){
            instance = new ToastInstance(context);
        }
        return instance;
    }
    public void showShortToast(Context context, String text){
        if (this.toast == null){
            this.toast = Toast.makeText(this.context, text, Toast.LENGTH_SHORT);
            int xOffset = this.toast.getXOffset();
            int yoffset = this.toast.getYOffset() + Y_OFFSET;
            this.toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, xOffset, yoffset);
        }else{
            this.toast.setText(text);
        }
        if (this.longToast != null){
            this.longToast.cancel();
        }
        this.toast.show();
    }

    public void showShortToast(Context context, int resid) {
        if (this.toast == null) {
            toast = Toast.makeText(this.context, this.context.getResources()
                    .getString(resid), Toast.LENGTH_SHORT);
            int xOffset = toast.getXOffset();
            int yOffset = toast.getYOffset() + Y_OFFSET;
            this.toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                    xOffset, yOffset);
        } else {
            this.toast.setText(resid);
        }
        if (this.longToast != null)
            this.longToast.cancel();
        this.toast.show();
    }

    public void showLongToast(Context context, String text){
        if (this.longToast == null){
            this.longToast = Toast.makeText(this.context, text, Toast.LENGTH_LONG);
            int xOffset = this.longToast.getXOffset();
            int yOffset = this.longToast.getYOffset() + Y_OFFSET;
            this.longToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, xOffset, yOffset);
        }else {
            this.longToast.setText(text);
        }
        if (this.toast != null){
            this.toast.cancel();
        }
        this.longToast.show();
    }

    public void showLongToast(Context context, int resid){
        if (this.longToast == null) {
            this.longToast = Toast.makeText(this.context, this.context.getResources().getString(resid), Toast.LENGTH_LONG);
            int xOffset = this.longToast.getXOffset();
            int yOffset = this.longToast.getYOffset() + Y_OFFSET;
            this.longToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, xOffset, yOffset);
        }else{
            this.longToast.setText(resid);
        }
        if (this.toast != null){
            this.toast.cancel();
        }
        this.longToast.show();
    }
}
