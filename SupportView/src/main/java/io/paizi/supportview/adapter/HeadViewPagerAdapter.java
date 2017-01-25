package io.paizi.supportview.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.paizi.supportview.BuildConfig;
import io.paizi.supportview.R;

/**
 * Created by pai on 2016/12/4.
 *
 */

public class HeadViewPagerAdapter<T> extends PagerAdapter {
    private Context context;
    private List<T> dataList;
    private List<View> viewList;
    private int screenWidth;
    private int screenHeight;

    public HeadViewPagerAdapter(Context context, List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
        viewList = new ArrayList<>();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(viewList.size() <= position||viewList.get(position)==null){
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            simpleDraweeView.setImageResource(R.drawable.urabe);
            viewList.add(simpleDraweeView);
        }
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
