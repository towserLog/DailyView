package io.paizi.supportview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.paizi.supportview.R;

/**
 * Created by pai on 2016/12/21.
 */

public class ListContentAdapter<T> extends PagerAdapter {
    private Context context;
    private List<T> dataList;
    private List<View> viewList;
    private int screenWidth;
    private int screenHeight;

    public ListContentAdapter(Context context, List<T> dataList) {
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
            final ListView recyclerView = (ListView) LayoutInflater.from(context)
                    .inflate(R.layout.list_view, null);
            ArrayList<String> arrayList = new ArrayList();
            for (int i=0;i<22;i++) {
                arrayList.add("--> "+i+" <---");
            }
            ListViewAdapter listViewAdapter =
                    new ListViewAdapter(context, arrayList);
            recyclerView.setAdapter(listViewAdapter);
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
