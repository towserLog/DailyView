package io.paizi.supportview.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import io.paizi.supportview.R;
import io.paizi.supportview.adapter.HeadViewPagerAdapter;
import io.paizi.supportview.adapter.ListContentAdapter;
import io.paizi.supportview.app.BaseActivity;

/**
 * Created by pai on 2016/12/21.
 *
 */

public class ListViewActivity extends BaseActivity {
    private static final String TAG = ListViewActivity.class.getSimpleName();
    private Context context;

    private ViewPager headViewPager;
    private ViewPager contentViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_drag_top);

        headViewPager = (ViewPager) findViewById(R.id.head_view_pager);
        int screenWidth = getScreenWidth();
        int picHeight = (int)(((float)screenWidth/391)*220f+0.5);
        ViewGroup.LayoutParams layoutParams = headViewPager.getLayoutParams();
        layoutParams.height = picHeight;
        layoutParams.width = screenWidth;
        headViewPager.setLayoutParams(layoutParams);

        contentViewPager = (ViewPager) findViewById(R.id.content_view_pager);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<22;i++) {
            arrayList.add("--> "+i+" <---");
        }
        HeadViewPagerAdapter headViewPagerAdapter = new HeadViewPagerAdapter<>(context, arrayList);
        ListContentAdapter contentPagerAdapter = new ListContentAdapter<>(context, arrayList);
        headViewPager.setAdapter(headViewPagerAdapter);
        contentViewPager.setAdapter(contentPagerAdapter);
    }

    private int getScreenWidth(){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        Log.i(TAG, "width--->"+displayMetrics.widthPixels+"   height--->"+displayMetrics.heightPixels);
        return displayMetrics.widthPixels;
    }
}
