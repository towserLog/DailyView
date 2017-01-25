package io.paizi.supportview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paizi.supportview.app.BaseActivity;
import io.paizi.supportview.widget.recycle.Divider;
import io.paizi.supportview.R;
import io.paizi.supportview.adapter.RecycleViewAdapter;
import io.paizi.supportview.widget.recycle.ItemTouchHelperCallback;

/**
 * Created by pai on 2016/12/12.
 *
 */

public class RecycleViewActicity extends BaseActivity{

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<33;i++) {
            arrayList.add("--> "+i+" <---");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(mContext, arrayList);
        recyclerView.setAdapter(recycleViewAdapter);

//      添加间隔线
        recyclerView.addItemDecoration(new Divider(mContext, LinearLayoutManager.VERTICAL));

//      添加手势（侧滑和拖动效果）
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(recycleViewAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
