package io.paizi.supportview.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paizi.supportview.R;
import io.paizi.supportview.widget.recycle.ItemDragListener;
import io.paizi.supportview.widget.recycle.ItemTouchHelperCallback;

/**
 * Created by pai on 2016/12/4.
 *
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> implements ItemDragListener {
    private Context context;
    private List<String> dataList;
    private  ItemDragListener mItemDragListener;

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

        holder.textView.setText(dataList.get(position));
//        final int mPosition = position;
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "第几个"+mPosition, Snackbar.LENGTH_SHORT)
//                        .show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.text_view)
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);

            textView = ButterKnife.findById(itemView, R.id.text_view);
        }
    }

    @Override
    public void onSwap(int formIndex, int toIndex) {
        Collections.swap(dataList, formIndex, toIndex);
        notifyItemMoved(formIndex, toIndex);
    }

    @Override
    public void onRemove(int index) {
        dataList.remove(index);
        notifyItemRemoved(index);
    }
}
