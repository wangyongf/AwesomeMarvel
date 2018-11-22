package com.yongf.android.contactsapp.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.yongf.android.contactsapp.R;
import com.yongf.android.contactsapp.TestMyRobolectricRunner;
import com.yongf.android.contactsapp.presentation.view.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
@Config(sdk = 25)
@RunWith(TestMyRobolectricRunner.class)
public class BitmapUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDrawable2BitmapNull() throws Exception {
        assertNull(BitmapUtils.drawable2Bitmap(null));
    }

    @Test
    public void testDrawable2Bitmap1() throws Exception {
        Bitmap bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        BitmapDrawable drawable = new BitmapDrawable(bitmap);
        assertEquals(bitmap, BitmapUtils.drawable2Bitmap(drawable));
    }

    private MainActivity mockActivity() {
        return Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void testDrawable2Bitmap2() throws Exception {
        MainActivity activity = mockActivity();
        Drawable drawable = activity.getResources().getDrawable(R.drawable.app_avatar);
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = BitmapUtils.drawable2Bitmap(drawable);
        assertEquals(bitmap.getWidth(), width);
        assertEquals(bitmap.getHeight(), height);
    }
}