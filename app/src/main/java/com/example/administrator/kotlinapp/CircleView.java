package com.example.administrator.kotlinapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2017/12/29.
 */

public class CircleView extends View {
    private float sweepAngle;//圆弧经过的角度

    private Paint rPaint;//矩形的画笔
    private Paint progressPaint;//圆弧的画笔
    private Paint textPaint;//进度画笔
    private int precent = 0;//更新百分比
    CircleAnim anim;//内部动画

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        anim = new CircleAnim();
    }

    private void init(Context context, AttributeSet attrs) {
        rPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rPaint.setStyle(Paint.Style.STROKE);//不填充
        rPaint.setColor(Color.GRAY);
        rPaint.setStrokeWidth(15);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStyle(Paint.Style.STROKE);//不填充
        progressPaint.setColor(Color.RED);
        progressPaint.setStrokeWidth(15);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.BLACK);
        textPaint.setFakeBoldText(true);//设置粗体
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int min = Math.min(getWidth(), getHeight());
        int centre = min / 2; // 获取圆心的x坐标
        int radius = centre - 15 / 2;// 半径
        RectF rectF = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        canvas.drawArc(rectF, 0, 360, false, rPaint);
        canvas.drawArc(rectF, 0, sweepAngle, false, progressPaint);//这里角度0对应的是三点钟方向，顺时针方向递增
        canvas.drawText(precent + "%", centre, centre, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measurewidth = 400;
        int measureheight = 400;
        measurewidth = resolveSize(measurewidth, widthMeasureSpec);
        measureheight = resolveSize(measureheight, heightMeasureSpec);
        setMeasuredDimension(measurewidth, measureheight);

    }

    public class CircleAnim extends Animation {

        public CircleAnim() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            sweepAngle = interpolatedTime * 360;
            precent = (int) (interpolatedTime * 100);
            invalidate();

        }
    }


    //设置动画时间
    public void setProgressNum(int time) {
        anim.setDuration(time);
        this.startAnimation(anim);
    }

}
