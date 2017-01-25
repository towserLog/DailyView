package io.paizi.supportview.widget.recycle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pai on 2017/1/10.
 * RecyclerView间隔线的绘制类
 */

public class Divider extends RecyclerView.ItemDecoration {

    private int mOrientation;

    private Drawable mDivider;

    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };

    public Divider(Context context, int orientation) {
        //获取主题中的drawable
        TypedArray typedArray =  context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        //mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if(mOrientation != LinearLayoutManager.HORIZONTAL&&
                mOrientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("rientation maybe LinearLayoutManager.HORIZONTAL" +
                    " or LinearLayoutManager.VERTICAL");
        }
        this.mOrientation = orientation;
    }

    /**
     * 在item绘制之之前执行
     * 这里绘制的内容会被item覆盖
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizonal(c, parent);
        }
//        drawVertical(c, parent);
//        drawHorizonal(c, parent);
    }

    /**
     * 在item绘制之后执行
     * 这里绘制的内容可以覆盖在item之上
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        if(mOrientation == LinearLayoutManager.VERTICAL){
//            drawVertical(c, parent);
//        }else if(mOrientation == LinearLayoutManager.HORIZONTAL){
//            drawHorizonal(c, parent);
//        }
    }

    /**
     * 画水平线
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams params
                    = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int top = childView.getBottom()
                    + params.bottomMargin + Math.round(ViewCompat.getTranslationY(childView));
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 画垂直线
     */
    private void drawHorizonal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams params
                    = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight()
                    +params.rightMargin + Math.round(ViewCompat.getTranslationX(childView));
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 设置item的偏移量
     * @param outRect 左上右下的偏移量
     * @param view    parent
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        try {
//            throw (new Exception());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0,0,mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
        }else if(mOrientation == LinearLayoutManager.HORIZONTAL){
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
