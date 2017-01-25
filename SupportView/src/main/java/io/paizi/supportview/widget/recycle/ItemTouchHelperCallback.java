package io.paizi.supportview.widget.recycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import io.paizi.supportview.R;

/**
 * Created by pai on 2017/1/23.
 * 实现recyclerview的侧滑和拖动交换
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemDragListener mItemDragListener;

    public ItemTouchHelperCallback(ItemDragListener itemDragListener) {
        mItemDragListener = itemDragListener;
    }

    /**
     * 用来设置支持的手势
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置响应移动的方向
        //同时支持左右
        int moveflags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //设置响应侧滑的方向
        //同时支持上下
        int swipeFlags =ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flag = makeMovementFlags(moveflags, swipeFlags);
        return flag;
    }


    /**
     * 移动时调用该方法
     * @param recyclerView
     * @param viewHolder    用户拖动的item
     * @param target        将要替换的item
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(viewHolder != target){
            mItemDragListener.onSwap(viewHolder.getAdapterPosition()
            , target.getAdapterPosition());
        }
        return false;
    }

    /**
     * 侧滑时调用该方法
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mItemDragListener.onRemove(viewHolder.getAdapterPosition());
    }


    /**
     * 可以对选中的条目做一些操作
     * 这里是改了下背景色，是选中的条目更明显
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            int color = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.alpha_black);
            viewHolder.itemView.setBackgroundColor(color);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 交互完成后调用
     * 这里把item恢复为默认
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.white);
        viewHolder.itemView.setBackgroundColor(color);
        viewHolder.itemView.setAlpha(1);
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 在item拖拽或侧滑时
     * 会不断的回调此方法来重绘item
     *
     * 为防止复用item出现异常，在此方法改变的item状态可以在
     * {@link #clearView(RecyclerView, RecyclerView.ViewHolder)}
     * 中恢复默认状态
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //根据移动的距离改变透明度实现渐变的效果
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            float alpha = 1-Math.abs(dX)/viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
        }

//        //限制最多只能滑动到一半
//        if(Math.abs(dX)< viewHolder.itemView.getWidth()/4) {
//            viewHolder.itemView.setTranslationX(dX);
//        }
        //
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 设置是否支持长安拖拽
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 设置支持侧滑
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
