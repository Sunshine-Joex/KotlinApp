package com.example.administrator.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/10.
 */

public class PieChartView extends View {
    Paint mPaint;
    private int[] mColors={Color.RED,Color.BLUE,Color.BLACK,Color.CYAN};
    private int[] angle={85,75,150,50};
    int currentAngle;

    public PieChartView(Context context) {
        super(context);
    }


    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
         mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
         mPaint.setStyle(Paint.Style.FILL);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int min = Math.min(getWidth(), getHeight());
        RectF rectF=new RectF(0,0,min,min);
         for (int i=0;i<mColors.length ;i++){
             mPaint.setColor(mColors[i]);
             canvas.drawArc(rectF,currentAngle,angle[i],true,mPaint);//TODO angle[i] 是圆弧划过的角度，不是end点
             currentAngle+=angle[i];
         }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measurewidth = 400;
        int measureheight = 400;
        measurewidth = resolveSize(measurewidth, widthMeasureSpec);
        measureheight = resolveSize(measureheight, heightMeasureSpec);
        setMeasuredDimension(measurewidth, measureheight);

    }
}
