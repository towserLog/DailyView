package io.paizi.supportview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.paizi.supportview.R;
import io.paizi.supportview.adapter.RecycleViewAdapter;

/**
 * Created by pai on 2016/12/12.
 *
 */

public class RecycleViewActicity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<222;i++) {
            arrayList.add("--> "+i+" <---");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecycleViewAdapter(this, arrayList));
    }
}
