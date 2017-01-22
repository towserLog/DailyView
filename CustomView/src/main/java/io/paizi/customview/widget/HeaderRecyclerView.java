package io.paizi.customview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by pai on 2017/1/21.
 * 添加header  和footer功能
 * 参考的listview写法来实现添加header和foot的功能
 */

public class HeaderRecyclerView extends RecyclerView {
    public static int HEADER_TYPE = -1;
    public static int FOOTER_TYPE = -2;

    private ArrayList<View> headerViewList = new ArrayList<>();
    private ArrayList<View> footerViewList = new ArrayList<>();
    //真正设置到recycleview的适配器
    private Adapter mAdapter;

    public HeaderRecyclerView(Context context) {
        super(context);
    }

    public HeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * 参考{@link android.widget.ListView#setAdapter(ListAdapter)}方法
     * 如果有headerView或者footerView
     * 就将传来的adapter装饰成HeadListAdapter
     */
    @Override
    public void setAdapter(Adapter adapter) {
        if(headerViewList.size()>0 || footerViewList.size()>0){
            if(!(mAdapter instanceof HeadListAdapter)){
                mAdapter = new HeadListAdapter(adapter);
            }
        }else{
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }

    /**
     * 参考{@link android.widget.ListView#addHeaderView(View)} (ListAdapter)}
     * 添加头布局
     */
    public void addHeaderView(View view){
        headerViewList.add(view);
        if (mAdapter != null){
            if(!(mAdapter instanceof HeadListAdapter)){
                mAdapter = new HeadListAdapter(mAdapter);
            }
        }
    }

    /**
     * 参考{@link android.widget.ListView#addFooterView(View)} (ListAdapter)}方法
     * 添加footerView
     */
    public void addFooterView(View view){
        footerViewList.add(view);
        if (mAdapter != null){
            if(!(mAdapter instanceof HeadListAdapter)){
                mAdapter = new HeadListAdapter(mAdapter);
            }
        }
    }

    public class HeadListAdapter extends RecyclerView.Adapter{
        private Adapter contentAdapter;

        public HeadListAdapter(Adapter contentAdapter) {
            if (headerViewList == null){
                headerViewList = new ArrayList<>();
            }
            if(footerViewList == null){
                footerViewList = new ArrayList<>();
            }
            this.contentAdapter = contentAdapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            毕竟headview和footerview
//            不涉及复用，也不会在adapter中绑定数据
//            只是按照要求返回个viewholader而已
            if(viewType == HEADER_TYPE){
                return  new HeaderViewHolder(headerViewList.get(0));
            }
            if(viewType == FOOTER_TYPE){
                return  new HeaderViewHolder(footerViewList.get(0));
            }
//            正常的就交给传来的adapter来处理了
            return contentAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //头部不用绑定数据
            if (position < headerViewList.size()){
                return;
            }

            //返回正常类型
            int adPosition = position - headerViewList.size();
            if(adPosition < contentAdapter.getItemCount()){
                contentAdapter.onBindViewHolder(holder, position);
                return;
            }

            //然后 footer  没了
        }

        @Override
        public int getItemViewType(int position) {
            //返回头部类型
            if (position < headerViewList.size()){
                return HEADER_TYPE;
            }

            //返回正常类型
            int adPosition = position - headerViewList.size();
            if(adPosition < contentAdapter.getItemCount()){
                return super.getItemViewType(position);
            }

            //返回footer类型
            return FOOTER_TYPE;
        }

        @Override
        public int getItemCount() {
            if(contentAdapter != null) {
                return contentAdapter.getItemCount()
                        + headerViewList.size()
                        + footerViewList.size();
            }else{
                return headerViewList.size()
                        + footerViewList.size();
            }
        }
    }

    static class HeaderViewHolder extends ViewHolder{
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
