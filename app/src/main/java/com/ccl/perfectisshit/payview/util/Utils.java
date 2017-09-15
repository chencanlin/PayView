package com.ccl.perfectisshit.payview.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by ccl on 2017/9/14.
 */

public class Utils {
    public static final int KEY_GET_WIDTH = 0;
    public static final int KEY_GET_HEIGHT = 1;

    public static int getScreenSize(Context context, int type) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return type == KEY_GET_WIDTH ? displayMetrics.widthPixels : displayMetrics.heightPixels;
    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2Px(Context context, int sp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
