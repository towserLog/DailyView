package io.paizi.supportview.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import io.paizi.supportview.R;
import io.paizi.supportview.adapter.FragmentAdapter;
import io.paizi.supportview.adapter.HeadViewPagerAdapter;
import io.paizi.supportview.ui.fragment.ListFragment;

public class DragTopActivity extends AppCompatActivity{
    private static final String TAG = DragTopActivity.class.getSimpleName();
    private Context context;

    private ViewPager headViewPager;
    private ViewPager contentViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        headViewPager = (ViewPager) findViewById(R.id.head_view_pager);
        contentViewPager = (ViewPager) findViewById(R.id.content_view_pager);
        
        setHeadViewPagerHeight();
        setViewPager();
    }

    private void setViewPager() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        for (int i=0;i<22;i++) {
            arrayList.add("--> "+i+" <---");
            fragmentArrayList.add(new ListFragment());
        }
        HeadViewPagerAdapter headViewPagerAdapter = new HeadViewPagerAdapter<>(context, arrayList);
        FragmentAdapter contentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentArrayList, arrayList);
        headViewPager.setAdapter(headViewPagerAdapter);
        contentViewPager.setAdapter(contentPagerAdapter);
    }

    private void setHeadViewPagerHeight() {
        int screenWidth = getScreenWidth();
        int picHeight = (int)(((float)screenWidth/391)*220f+0.5);
        ViewGroup.LayoutParams layoutParams = headViewPager.getLayoutParams();
        layoutParams.height = picHeight;
        layoutParams.width = screenWidth;
        //layout_scrollFlags="scroll|enterAlwaysCollapsed"时一定要设置setMinimumHeight
        headViewPager.setMinimumHeight(picHeight);
        headViewPager.setLayoutParams(layoutParams);
    }

    private int getScreenWidth(){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        Log.i(TAG, "width--->"+displayMetrics.widthPixels+"   height--->"+displayMetrics.heightPixels);
        return displayMetrics.widthPixels;
    }
}
