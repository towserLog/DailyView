package io.paizi.supportview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import io.paizi.supportview.R;

/**
 * Created by pai on 2016/12/4.
 *
 */

public class ContentPagerAdapter<T>  extends PagerAdapter {
    private Context context;
    private List<T> dataList;
    private List<View> viewList;
    private int screenWidth;
    private int screenHeight;

    public ContentPagerAdapter(Context context, List<T> dataList) {
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
//        return view == object;
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(viewList.size() <= position||viewList.get(position)==null){
            final RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context)
                    .inflate(R.layout.recycle_view, null);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            ArrayList<String> arrayList = new ArrayList();
            for (int i=0;i<22;i++) {
                arrayList.add("--> "+i+" <---");
            }
            RecycleViewAdapter recycleViewAdapter =
                    new RecycleViewAdapter(context, arrayList);
            recyclerView.setAdapter(recycleViewAdapter);
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Log.i("ContentPagerAdapter", "width--->"+recyclerView.getHeight()+"   height--->"+recyclerView.getWidth());
                }
            });
//            Log.i("ContentPagerAdapter", "width--->"+recyclerView.getHeight()+"   height--->"+recyclerView.getWidth());
            viewList.add(recyclerView);
        }
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
