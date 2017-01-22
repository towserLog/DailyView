package io.paizi.myutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by pai on 2017/1/15.
 *
 */

public class StatusUtil {
    private static int DEFAULT_STATUS_BAR_ALPHA = 112;

    /**
     * 设置状态栏颜色
     * @param activity   设置状态栏的activity
     * @param color      设置的颜色
     * @param alpha      状态栏颜色的不透明度
     */
    public static void setColor(Activity activity, int color, int alpha){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(calqculateStatusColor(color, alpha));
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup rootView = (ViewGroup)activity.getWindow().findViewById(android.R.id.content);
            View childAt = rootView.getChildAt(0);
            childAt.setBackgroundColor(color);
            setRootView(activity);
        }
    }

    /**
     * 抄来的方法，用于适配4.4.4 还未测试
     * @param activity
     */
    private static void setRootView(Activity activity) {
//        ViewGroup rootView = (ViewGroup)((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
        ViewGroup rootView = (ViewGroup)activity.findViewById(android.R.id.content);
        //使布局不延伸到状态栏
        rootView.setFitsSystemWindows(true);
        //防止NavigationView不延伸到状态栏
        rootView.setClipToPadding(true);
    }

    /**
     * 设置状态栏透明的方法
     * @param activity      设置状态栏的activity
     * @param setRootView   是否执行setRootView()方法
     */
    public static void setStatusTransparent(Activity activity,boolean setRootView){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            return;
        }
        transparentStatusBar(activity);
        if(setRootView)
            setRootView(activity);
    }

    private static void transparentStatusBar(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup rootView = (ViewGroup) activity.getWindow().findViewById(android.R.id.content);
            View childAt = rootView.getChildAt(0);
            childAt.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    /**
     * 通过上下文获取状态栏高度
     */
    private static int getStatusBarHeight(Context context){
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
    /**
     * @param color  颜色
     * @param alpha  透明度
     * @return  返回具有了透明度的颜色
     */
    private static int calqculateStatusColor(int color, int alpha){
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        System.out.print(""+null);
        return Color.argb(alpha, red, green, blue);
    }
}
