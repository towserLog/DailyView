package io.paizi.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liubin on 2017/3/14.
 * 一些canvas的基本操作
 */

public class CanvasView extends View {
    private  final String TAG = this.getClass().getSimpleName();

    //定义画笔
    private Paint mPaint;
    //宽高
    private int mHeight, mWidth;

    public CanvasView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
//        translate(canvas);

//        scale(canvas);

//        rotate(canvas);

//        canvasSave(canvas);

//        canvasLayer(canvas);

        canvasPicture(canvas);
    }
    //旋转
    private void rotate(Canvas canvas) {
        //将坐标系原点移动到画布中心
        canvas.translate(mWidth/2, mHeight/2);

        RectF rectF = new RectF(0,-200,200, 0);

//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF, mPaint);
//        canvas.rotate(180);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
        canvas.rotate(180, 0, 100);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
    }

    //移动  每次translate都是基于当前位置移动
    private void translate(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.translate(100, 100);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.translate(100, 100);
        canvas.drawCircle(0, 0, 100, mPaint);
    }

    //缩放
    private void scale(Canvas canvas) {
        canvas.translate(mWidth/2, mHeight/2);
        RectF rectF = new RectF(0, -200, 200 ,0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
        //直接缩放
//        canvas.scale(0.5F, 0.5F);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);

        //缩放画布  <-- 缩放中心向右偏移了100个单位
//        canvas.scale(0.5F, 0.5F, 100, 0);   //先移动100  然后缩放  然后在移动  -（100*0.5）
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF, mPaint);

        //缩放画布  sx为负值时绕Y轴翻转 sy为负值是绕X轴翻转
        canvas.scale(-0.5F, -0.5F);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
    }

    //错切
    private void skew(Canvas canvas){
        canvas.translate(mWidth/2, mHeight/2);

        RectF rectF = new RectF(0, 0, 100, 100);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
        //调用的顺序不同结果也不同
        canvas.skew(0, 1);
        canvas.skew(1, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
    }

    //用缩放画一个有趣的图形
    private void scaleShape(Canvas canvas){
        canvas.translate(mWidth/2, mHeight/2);

        RectF rectF = new RectF(-200F, -200F, 200F, 200F);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        for (int i = 0; i < 200; i++) {
            canvas.drawRect(rectF, mPaint);
            canvas.scale(0.99F, 0.99F);
        }
    }

    //用旋转画一个有意思的图形
    private void rotateShape(Canvas canvas){
        canvas.translate(mWidth/2, mHeight/2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        int smallCircle = 300;
        int bigCircle = 312;
        int rotate = 6;
        canvas.drawCircle(0, 0, smallCircle, mPaint);
        canvas.drawCircle(0, 0, bigCircle, mPaint);

        for (int i = 0; i < 360; i+=rotate) {
            canvas.drawLine(smallCircle, 0, bigCircle, 0, mPaint);
            canvas.rotate(rotate);
        }
    }

    //save方法保存了xy轴的状态 不会影响已经draw的图形
    private void canvasSave(Canvas canvas){
        Paint background = new Paint();
        Paint line = new Paint();

        line.setStrokeWidth(4F);
        background.setColor(Color.GRAY);
        line.setColor(Color.RED);

        int px = 500;
        int py = 500;
        RectF rectF = new RectF(0, 0, px, py);

        canvas.drawRect(rectF, background);
        canvas.save(); /** 保存状态*/

        canvas.rotate(90, px/2, py/2);

        //旋转之后画的 ，虽然在save之后执行，但是在restore后不会改变
        canvas.drawLine(px/2, 0, 0, py/2, line);
        canvas.drawLine(px/2, 0, px, py/2, line);
        canvas.drawLine(px/2, 0, px/2, py, line);

        canvas.restore();  /** 恢复状态*/

        //在注释save和restore前后会发现效果不同
        canvas.drawCircle(400, 400, 30, line);
    }


    private final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                     | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                     | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    /**
     * @param canvas
     */
    //layer的保存还可以理解，layerde1resotre完全搞不懂
    private void canvasLayer(Canvas canvas){
//        canvas.translate(mWidth/2, mHeight/2);

        canvas.saveLayer(0, 0, 600, 600, mPaint, LAYER_FLAGS);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        canvas.drawColor(Color.YELLOW);
        canvas.drawCircle(100, 100, 100, mPaint);
        canvas.saveLayer(0, 0, 600, 600, mPaint, LAYER_FLAGS);
//        canvas.saveLayerAlpha(0, 0, 600, 600, 0xff, LAYER_FLAGS);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(150, 150, 100, mPaint);
//        canvas.saveLayerAlpha(50, 50, 250, 250, 0x88, LAYER_FLAGS);
        canvas.restore();
    }

    /**
     * @return 录制一个Picture
     */
    private Picture recordPicture(){

        Picture picture = new Picture();
        Canvas canvas = picture.beginRecording(300, 300);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.translate(200f, 200f);
        canvas.drawCircle(0, 0, 150, paint);

        picture.endRecording();

        return  picture;
    }

    /**
     * @param canvas 将picture绘制到canvas上
     */
    private void canvasPicture(Canvas canvas){
        Picture picture = recordPicture();

//        canvas.drawPicture(picture);
        //此方法会将picture压缩到
//        canvas.drawPicture(picture, new RectF(0, 0, picture.getWidth(), 200));

        PictureDrawable pictureDrawable = new PictureDrawable(picture);
        pictureDrawable.setBounds(0,0,picture.getWidth()/2,picture.getHeight());
        pictureDrawable.draw(canvas);

    }

}
