package io.piazi.quiz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by towse on 2017/10/9.
 */

public class CanvasView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //测试drawtext时 baseline的位置
        quizBaseLine(canvas);

    }

    private void quizBaseLine(Canvas canvas) { paint.setTextSize(90.0f);

        paint.setColor(Color.BLACK);
        canvas.drawLine(50.0f, 100.0f, 400.0f, 100.0f, paint);
        canvas.drawLine(50.0f, 120.0f, 400.0f, 120.0f, paint);
        //baseline
        paint.setColor(Color.RED);
        canvas.drawLine(50.0f, 170.0f, 400.0f, 170.0f, paint);
        paint.setColor(Color.BLACK);
        canvas.drawLine(50.0f, 190.0f, 400.0f, 190.0f, paint);


        paint.setColor(Color.BLUE);
        canvas.drawText("asdfgyq", 50.0f, 170.0f, paint);
    }
}
