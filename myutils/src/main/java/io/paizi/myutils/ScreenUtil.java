package io.paizi.myutils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by pai on 2017/2/22.
 * 用于获取屏幕的参数
 */

public class ScreenUtil {

    public static DisplayMetrics getScreenMetrics(Activity activity){
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
//        int height = metric.heightPixels;
        return metric;
    }
}
