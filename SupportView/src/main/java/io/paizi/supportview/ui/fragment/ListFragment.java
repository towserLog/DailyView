package io.paizi.supportview.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.paizi.supportview.R;
import io.paizi.supportview.adapter.RecycleViewAdapter;
import io.paizi.supportview.anim.ItemAnim;

/**
 * Created by pai on 2016/12/21.
 *
 */

public class ListFragment extends Fragment {
    private RecyclerView contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(contentView==null){
            contentView = (RecyclerView) inflater
                    .inflate(R.layout.recycle_view, null, false);
            contentView
                    .setLayoutManager(new LinearLayoutManager(inflater.getContext()));
            contentView.setItemAnimator(new ItemAnim());

            ArrayList<String> arrayList = new ArrayList();
            for (int i=0;i<222;i++) {
                arrayList.add("--> "+i+" <---");
            }
            contentView.setAdapter(
                    new RecycleViewAdapter(inflater.getContext(), arrayList));
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if(parent != null){
            parent.removeView(contentView);
        }
        return contentView;
    }


}
