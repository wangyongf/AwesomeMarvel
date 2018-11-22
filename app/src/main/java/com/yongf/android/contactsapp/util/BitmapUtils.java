package com.yongf.android.contactsapp.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Bitmap 变换相关工具类
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/21.
 */
public class BitmapUtils {

    /**
     * convert a {@link Drawable} to a {@link Bitmap}
     *
     * @param drawable the source drawable
     * @return the converted bitmap if drawable is not null; null if drawable is null
     */
    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}
