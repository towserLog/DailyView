package io.paizi.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import io.paizi.customview.R;

/**
 * Created by towser on 2017/4/25.
 * 自定义圆角图片
 */

public class RoundImageView extends ImageView {

    private Paint mPaint;

    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    private Bitmap mMaskBitmap;

    private WeakReference<Bitmap> mWeakBitmap;

    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;

    /**
     * 圆角大小的默认值
     */
    private static final int BODER_RADIUS_DEFAULT = 10;

    /**
     * 圆角的大小
     */
    private int mBorderRadius;

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        //// TODO: 2017/4/26  还没读懂源码，但是能猜出是充xml中读取配置
        mBorderRadius = typedArray.getDimensionPixelOffset(R.styleable.RoundImageView_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));
        type = typedArray.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);

        typedArray.recycle();
    }

    public RoundImageView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /** 如果是圆形图片， 将宽高设置成相同，以最小值为准 */
        if(type == TYPE_CIRCLE){
            int width = Math.min(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /** 缓存中取出bitmap     */
        Bitmap bitmap = mWeakBitmap==null ? null : mWeakBitmap.get();

        if(bitmap==null || bitmap.isRecycled()){
            //拿到drawable
            Drawable drawable = getDrawable();
            //获取drawable的宽高

            int dwidth = drawable.getIntrinsicWidth();
            int dheight =  drawable.getIntrinsicHeight();

            if(drawable != null){
                //创建bitmap
                bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                float scale = 1.0f;
                Canvas drawCanvas = new Canvas(bitmap);

                if(type == TYPE_ROUND){
                    // TODO: 2017/4/27 没懂
                    //如果图片的宽高和view的宽高不匹配，计算出需要缩放的比例，缩放后图片的宽高，一定要大于view的宽高
                    //所以这里取最大值
                    scale = Math.max(getWidth()*1.0f/dwidth, getHeight()*1.0f/dheight);
                }else{
                    scale = getWidth()*1.0f / Math.min(dwidth, dheight);
                }

                //根据缩放比例，设置bound .相当于缩放图片了
                drawable.setBounds(0, 0, (int)scale*dwidth, (int)scale*dheight);

                drawable.draw(drawCanvas);
                if(mMaskBitmap==null ||mMaskBitmap.isRecycled()){
                    mMaskBitmap = getBitMap();
                }
                //draw bitmap
                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);
                //绘制形状
                drawCanvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
                mPaint.setXfermode(null);
                canvas.drawBitmap(bitmap, 0,0, null);
                mWeakBitmap = new WeakReference<Bitmap>(bitmap);
            }
        }
    }

    /**
     * 绘制
     */
    private Bitmap getBitMap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if(type == TYPE_ROUND){
            canvas.drawRoundRect(
                    new RectF(0, 0, getWidth(), getHeight()), mBorderRadius, mBorderRadius, paint);
        }else{
            canvas.drawCircle(getWidth()/2, getHeight()/2, mBorderRadius, paint);
        }
        return bitmap;
    }

    //清除缓存
    @Override
    public void invalidate() {
        mWeakBitmap = null;
        if(mMaskBitmap != null){
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }
}
