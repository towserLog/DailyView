package io.paizi.customview.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paizi.customview.R;
import io.paizi.customview.view.HeaderRecyclerView;

/**
 * Created by pai on 2017/1/21.
 * 测试HeaderRecyclerView
 * {@link HeaderRecyclerView}
 */

public class HeadRecycleActivity extends BaseActivity {

    @BindView(R.id.header_recycleview)
    HeaderRecyclerView mHeaderRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_recycle);
        ButterKnife.bind(this);

        TextView headerView = new TextView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        headerView.setLayoutParams(params);
        headerView.setText("我是HeaderView");
        mHeaderRecyclerView.addHeaderView(headerView);

        TextView footerView = new TextView(this);
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        footerView.setLayoutParams(params);
        footerView.setText("我是FooterView");
        mHeaderRecyclerView.addFooterView(footerView);

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i=0;i<22;i++) {
            arrayList.add("--> "+i+" <---");
        }
        mHeaderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHeaderRecyclerView.setAdapter(new MyRecycleViewAdapter(mContext, arrayList));
    }

    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private Context context;
        private List<String> dataList;

        public MyRecycleViewAdapter(Context context, List<String> dataList) {
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


    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
