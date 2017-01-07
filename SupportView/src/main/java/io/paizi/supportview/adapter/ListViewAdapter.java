package io.paizi.supportview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.paizi.supportview.R;

/**
 * Created by pai on 2016/12/21.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> listData;

    public ListViewAdapter(Context context, List<String> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData==null?0:listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.content_list_item, null, false);

            TextView textView = (TextView) itemView.findViewById(R.id.text_view);
            viewHolder.textView = textView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText("---->       "+position+"       <----");
        return convertView;
    }

    public static class ViewHolder{
        TextView textView;
    }
}
