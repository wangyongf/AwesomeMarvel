package com.yongf.android.contactsapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yongf.android.contactsapp.R;

/**
 * 圆形进度条
 * // TODO: 增加可配置属性的注释
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/21.
 */
public class RoundedProgressView extends View {

    // TODO: 修改一下默认参数使其生效~
    private static final int DEFAULT_START_ANGLE = -90;
    private static final int DEFAULT_PROGRESS_WIDTH = 5;
    private static final int DEFAULT_PROGRESS = 10;
    private static final int DEFAULT_INNER_CIRCLE_RADIUS = 100;
    private static final String DEFAULT_INNER_CIRCLE_COLOR = "#EEFF06";
    private static final String DEFAULT_PROGRESS_BG = "#FF7281E1";
    private static final String DEFAULT_PROGRESS_COLOR = "#FFDA0F0F";
    private static final String DEFAULT_TEXT_COLOR = "#FF000000";
    private static final int DEFAULT_TEXT_SIZE = 30;

    /**
     * 进度条的起始角度
     */
    private int startAngle;
    /**
     * 进度条的宽度
     */
    private int progressWidth;
    /**
     * 当前进度
     */
    private int progress;
    /**
     * 内部圆的半径
     */
    private int innerCircleRadius;
    /**
     * 圆形进度条内部的填充颜色
     */
    private int innerCircleColor;
    /**
     * 进度条背景色（外圈圆）
     */
    private int progressBg;
    /**
     * 进度条颜色
     */
    private int progressColor;
    /**
     * 控件中央的文字
     */
    private String text;
    /**
     * 进度条中央文字大小
     */
    private int textSize;
    /**
     * 进度条中央文字颜色
     */
    private int textColor;

    private Paint mPaint;               // 画笔

    public RoundedProgressView(Context context) {
        this(context, null);
    }

    public RoundedProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundedProgressView);
        for (int i = 0; i < array.length(); i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.RoundedProgressView_startAngle:
                    startAngle = array.getInteger(attr, DEFAULT_START_ANGLE);
                    break;
                case R.styleable.RoundedProgressView_progressWidth:
                    progressWidth = (int) array.getDimension(attr, getDefaultProgressWidth());
                    break;
                case R.styleable.RoundedProgressView_progress:
                    progress = array.getInteger(attr, DEFAULT_PROGRESS);
                    break;
                case R.styleable.RoundedProgressView_innerCircleRadius:
                    innerCircleRadius = (int) array.getDimension(attr, getDefaultInnerCircleRadius());
                    break;
                case R.styleable.RoundedProgressView_innerCircleColor:
                    innerCircleColor = array.getColor(attr, getColor(DEFAULT_INNER_CIRCLE_COLOR));
                    break;
                case R.styleable.RoundedProgressView_progressBg:
                    progressBg = array.getColor(attr, getColor(DEFAULT_PROGRESS_BG));
                    break;
                case R.styleable.RoundedProgressView_progressColor:
                    progressColor = array.getColor(attr, getColor(DEFAULT_PROGRESS_COLOR));
                    break;
                case R.styleable.RoundedProgressView_text:
                    text = array.getString(attr);
                    break;
                case R.styleable.RoundedProgressView_textSize:
                    textSize = (int) array.getDimension(attr, getDefaultTextSize());
                    break;
                case R.styleable.RoundedProgressView_textColor:
                    textColor = array.getColor(attr, getColor(DEFAULT_TEXT_COLOR));
                    break;
                default:
                    break;
            }
        }
        array.recycle();

        setupPaint();
    }

    /**
     * 初始化画笔
     */
    private void setupPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private int getDefaultProgressWidth() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_PROGRESS_WIDTH, getResources().getDisplayMetrics());
    }

    private int getDefaultTextSize() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics());
    }

    private int getDefaultInnerCircleRadius() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_INNER_CIRCLE_RADIUS, getResources().getDisplayMetrics());
    }

    private @ColorInt
    int getColor(String color) {
        try {
            return Color.parseColor(color);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = innerCircleRadius * 2 + progressWidth * 2;
        int height = width;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取圆心、半径
        int cx = Math.min(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        int cy = cx;

        // 绘制内部的圆
        drawInnerCircle(canvas, cx, cy);

        // 绘制进度条背景
        drawOuterCircle(canvas, cx, cy);

        // 绘制进度圆弧
        drawProgress(canvas, cx, cy);

        // 绘制进度文字
        drawProgressText(canvas, cx, cy);
    }

    /**
     * 绘制内部的圆
     *
     * @param canvas 画布
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     */
    private void drawInnerCircle(Canvas canvas, int cx, int cy) {
        mPaint.setColor(innerCircleColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, cy, innerCircleRadius, mPaint);
    }

    /**
     * 绘制进度条背景
     *
     * @param canvas 画布
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     */
    private void drawOuterCircle(Canvas canvas, int cx, int cy) {
        mPaint.setColor(progressBg);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressWidth);
        canvas.drawCircle(cx, cy, innerCircleRadius, mPaint);
    }

    /**
     * 绘制进度圆弧
     *
     * @param canvas 画布
     * @param cx     圆心的横坐标
     * @param cy     圆心的纵坐标
     */
    private void drawProgress(Canvas canvas, int cx, int cy) {
        mPaint.setColor(progressColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressWidth);
        int radius = innerCircleRadius;
        RectF rectF = new RectF(cx - radius, cy - radius, cx + radius, cy + radius);
        float sweepAngle = progress * 360.0f / 100;
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mPaint);
    }

    /**
     * 文案绘制
     *
     * @param canvas 画布
     * @param cx     圆心横坐标
     * @param cy     圆心的纵坐标
     */
    private void drawProgressText(Canvas canvas, int cx, int cy) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setStrokeWidth(0);
        float textWidth = mPaint.measureText(text);
        canvas.drawText(text, cx - textWidth / 2, cy + textSize / 2, mPaint);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }
        this.progress = progress;
        postInvalidate();
    }

    public void setText(@Nullable String text) {
        this.text = text;
        postInvalidate();
    }
}
