package io.paizi.customview.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pai on 2017/1/21.
 *
 */

public class HeaderListAdapter extends RecyclerView.Adapter {

    private List<View> headerViewList;
    private List<View> footerViewList;

    public HeaderListAdapter(List<View> headerViewList,
                             List<View> footerViewList,
                             RecyclerView.Adapter mAdapter) {
        this.headerViewList = headerViewList;
        this.footerViewList = footerViewList;
        this.mAdapter = mAdapter;
    }

    RecyclerView.Adapter mAdapter;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
