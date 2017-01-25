package io.paizi.supportview.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.paizi.supportview.app.BaseFragment;
import io.paizi.supportview.widget.recycle.Divider;
import io.paizi.supportview.R;
import io.paizi.supportview.adapter.RecycleViewAdapter;
import io.paizi.supportview.widget.recycle.ItemAnim;

/**
 * Created by pai on 2016/12/21.
 *
 */

public class ListFragment extends BaseFragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(recyclerView ==null){
            recyclerView = (RecyclerView) inflater
                    .inflate(R.layout.recycle_view, null, false);
            recyclerView
                    .setLayoutManager(new LinearLayoutManager(inflater.getContext()/*, LinearLayoutManager.HORIZONTAL, false*/));
//
//            recyclerView
//                    .setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setItemAnimator(new ItemAnim());

            ArrayList<String> arrayList = new ArrayList();
            for (int i=0;i<222;i++) {
                arrayList.add("--> "+i+" <---");
            }
            recyclerView.addItemDecoration(new Divider(getContext(), LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(
                    new RecycleViewAdapter(inflater.getContext(), arrayList));
        }
        ViewGroup parent = (ViewGroup) recyclerView.getParent();
        if(parent != null){
            parent.removeView(recyclerView);
        }
        return recyclerView;
    }


}
