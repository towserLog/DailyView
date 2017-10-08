package io.paizi.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by towser on 2017/4/25.
 */

public class CustomDrawableView extends View {
    public CustomDrawableView(Context context) {
        super(context);
    }

    public CustomDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
