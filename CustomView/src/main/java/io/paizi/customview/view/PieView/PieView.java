package io.paizi.customview.view.PieView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by liubin on 2017/3/14.
 * 画一个饼图
 */

public class PieView extends View {
    private final String TAG = this.getClass().getSimpleName();

    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //定义画笔
    private Paint mPaint;
    //宽高
    private int mHeight, mWidth;
    //数据
    private ArrayList<PieData> mData;
    //绘制起始角度
    private float mStartAngle = 0;

    public PieView(Context context) {
        super(context);
        initPaint();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mData == null){
            return;
        }
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight/2);  //将画布坐标原点移到中心位置
        float r = (float) (Math.min(mHeight, mWidth) / 2 * 0.8);  //饼图的半径
        RectF rectF = new RectF(-r, -r, r, r);     //饼状图绘制区域

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            mPaint.setColor(mColors[i]);
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();
        }
    }

    //设置起始角度
    public void setStartAngle(int mStartAngle){
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setData(ArrayList<PieData> datas){
        mData = datas;
        initData(mData);
        invalidate();
    }

    private void initData(ArrayList<PieData> datas) {
        if (datas == null || datas.size() <= 0){    //数据有问题，直接返回
            return;
        }
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);

            sumValue += pieData.getValue();

            int j = i % mColors.length;     //设置颜色
            pieData.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int j = 0; j < mData.size(); j++) {
            PieData pieData = mData.get(j);

            float percentage = pieData.getValue() / sumValue;   //求出百分比
            float angle = percentage * 360;                     //求出对应的角度

            pieData.setAngle(angle);
            pieData.setPercentage(percentage);
            sumAngle += angle;

            Log.i(TAG, "pieData getAngle   " + pieData.getAngle());
        }


    }


}
