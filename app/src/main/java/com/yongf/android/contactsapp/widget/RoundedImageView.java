package com.yongf.android.contactsapp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.yongf.android.contactsapp.R;
import com.yongf.android.contactsapp.util.BitmapUtils;

/**
 * A Round ImageView
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/20.
 */
public class RoundedImageView extends AppCompatImageView {

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 圆形 ImageView 的半径
     */
    private int mRadius;
    /**
     * 图片的缩放比例
     */
    private float mScale;
    /**
     * 图片背景的缩放（Matrix）
     */
    private Matrix mMatrix;

    private int mSelecetedOuterBg;                              // 选中图像外圈背景颜色
    private int mSelectedBorderWidth;                            // 选中图像外圈宽度, in d(i)p
    private boolean isSelected;                                 // 当前是否被选中

    public RoundedImageView(Context context) {
        this(context, null);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();

        mSelectedBorderWidth = getResources().getDimensionPixelOffset(R.dimen.app_roundedWidth);
        mSelecetedOuterBg = getResources().getColor(R.color.app_colorRounded);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 将宽高中的较小值作为圆形 ImageView 的尺寸（直径）
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = size / 2;
        int width = size + mSelectedBorderWidth * 2;
        int height = width;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawBitmap(canvas, drawable);
            drawBorder(canvas);
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 绘制外圈边框
     *
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {
        if (!isSelected) {
            return;
        }
        mPaint.setColor(mSelecetedOuterBg);
        mPaint.setStrokeWidth(mSelectedBorderWidth);
        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.STROKE);
        int cx = mRadius + mSelectedBorderWidth;
        int cy = mRadius + mSelectedBorderWidth;
        canvas.drawCircle(cx, cy, mRadius, mPaint);
    }

    /**
     * 绘制中央的图片部分
     *
     * @param canvas   画布
     * @param drawable 待绘制的图像
     */
    private void drawBitmap(Canvas canvas, Drawable drawable) {
        Bitmap bitmap = BitmapUtils.drawable2Bitmap(drawable);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mScale = (mRadius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());
        mMatrix.setScale(mScale, mScale);
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        // 准备完毕，绘制圆形图像
        int cx = mRadius + mSelectedBorderWidth;
        int cy = mRadius + mSelectedBorderWidth;
        canvas.drawCircle(cx, cy, mRadius, mPaint);
    }

    /**
     * 设置当前图像是否被选中，以确定是否绘制边框
     *
     * @param selected true-被选中，将绘制边框；false-未被选中，不再绘制边框
     */
    public void setIsSelected(boolean selected) {
        this.isSelected = selected;
        invalidate();
    }
}
