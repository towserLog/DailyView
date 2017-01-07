package io.paizi.supportview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.paizi.supportview.R;

/**
 * Created by pai on 2016/12/4.
 *
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{
    private Context context;
    private List<String> dataList;

    public RecycleViewAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.content_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText("---->       "+position+"       <----");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
