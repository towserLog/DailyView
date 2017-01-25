package io.paizi.supportview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import io.paizi.supportview.app.BaseActivity;
import io.paizi.supportview.R;
import io.paizi.supportview.adapter.HeadViewPagerAdapter;

/**
 * Created by pai on 2016/12/12.
 *
 */

public class ViewPagerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<22;i++) {
            arrayList.add("--> "+i+" <---");
        }
        viewPager.setAdapter(new HeadViewPagerAdapter<>(this, arrayList));
    }
}
