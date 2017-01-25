package io.paizi.supportview.ui.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paizi.myutils.StatusUtil;
import io.paizi.supportview.R;
import io.paizi.supportview.adapter.FragmentAdapter;
import io.paizi.supportview.adapter.HeadViewPagerAdapter;
import io.paizi.supportview.app.BaseActivity;
import io.paizi.supportview.ui.fragment.ListFragment;

/**
 *
 */
public class DragTopActivity extends BaseActivity {
    private static final String TAG = DragTopActivity.class.getSimpleName();

    @BindView(R.id.head_view_pager)
    ViewPager headViewPager;

    @BindView(R.id.content_view_pager)
    ViewPager contentViewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_top);

        StatusUtil.setStatusTransparent(this, false);
        ButterKnife.bind(this);

        setHeadViewPagerHeight();
        setViewPager();
    }

    private void setViewPager() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        for (int i=0;i<4;i++) {
            arrayList.add("--> "+i+" <---");
            fragmentArrayList.add(new ListFragment());
//            TabLayout.Tab tab = tabLayout.newTab().setText("第"+i+"页");
//            tabLayout.addTab(tab);
        }
        HeadViewPagerAdapter headViewPagerAdapter = new HeadViewPagerAdapter<>(mContext, arrayList);
        FragmentAdapter contentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentArrayList, arrayList);
        headViewPager.setAdapter(headViewPagerAdapter);
        contentViewPager.setAdapter(contentPagerAdapter);
        //将tablayout和viewpager关联起来
//        LinearLayoutCompat
//        for (int i=0;i<22;i++) {
//            TabLayout.Tab tab = tabLayout.newTab().setText("第"+i+"页");
//            tabLayout.addTab(tab);
//        }
        tabLayout.setupWithViewPager(contentViewPager);
//        for (int i=0;i<22;i++) {
//            tabLayout.getTabAt(i).setText("第"+i+"页");
//        }
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
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        Log.i(TAG, "width--->"+displayMetrics.widthPixels+"   height--->"+displayMetrics.heightPixels);
        return displayMetrics.widthPixels;
    }
}
