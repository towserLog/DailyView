package io.paizi.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by pai on 2017/2/8.
 * 流式布局
 */

public class FlowLayot extends ViewGroup {

    private ArrayList<ArrayList<View>> lines;

    private int lastUseWidth;

    public FlowLayot(Context context) {
        super(context);
    }

    public FlowLayot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        lines = new ArrayList<>();
        lines.add(new ArrayList<View>());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        //这里先测量子view的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            int childWidth = lp.leftMargin + view.getMeasuredWidth() + lp.rightMargin;
            int childHeight = lp.topMargin + view.getHeight() + lp.bottomMargin;
            //当这行放不下这个标签的时候，换行
            if(lineWidth + childWidth > measureWidth){
                //比较获取长度最长的一行
                width = Math.max(lineWidth, width);
                lineWidth = width;
                //因为换行，所以确定了上一行高度，将其计算入总高度
                height += lineHeight;
                lineHeight = childHeight;
            }else{
                lineWidth = lineWidth + width;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            //如果最后一个子view的话，计算宽高
            if(i == getChildCount()-1){
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        int resultWidth = (widthMode == MeasureSpec.EXACTLY ? measureWidth:width);
        int resultHeight = (heightMode == MeasureSpec.EXACTLY ? measureHeight:height);
        //将测量好的宽和高保存
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
