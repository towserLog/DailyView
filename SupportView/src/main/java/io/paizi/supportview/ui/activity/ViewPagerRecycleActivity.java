package io.paizi.supportview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import io.paizi.supportview.R;
import io.paizi.supportview.adapter.ContentPagerAdapter;

/**
 * Created by pai on 2016/12/13.
 *
 */

public class ViewPagerRecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_recycle);
        ViewPager view_pager = (ViewPager) findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<22;i++) {
            arrayList.add("--> "+i+" <---");
        }
        view_pager.setAdapter(new ContentPagerAdapter(this,arrayList));
    }
}

