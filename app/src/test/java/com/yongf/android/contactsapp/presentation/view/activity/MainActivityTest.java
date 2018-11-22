package com.yongf.android.contactsapp.presentation.view.activity;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.yongf.android.contactsapp.R;
import com.yongf.android.contactsapp.TestMyRobolectricRunner;
import com.yongf.android.contactsapp.domain.model.ContactInfo;
import com.yongf.android.contactsapp.util.ContactsHelper;
import com.yongf.android.contactsapp.widget.VerticalViewPager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
@Config(sdk = 25)
@RunWith(TestMyRobolectricRunner.class)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private MainActivity mockActivity() {
        return Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    /**
     * 当页面 onResume 执行完后，进度条应该隐藏了
     *
     * @throws Exception
     */
    @Test
    public void testShowMainActivity() throws Exception {
        MainActivity activity = mockActivity();
        ProgressBar progressBar = activity.findViewById(R.id.progressBar);
        assertFalse(progressBar.getVisibility() == View.VISIBLE);
    }

    /**
     * 更新页面数据，测试展示的 Tab 数量是否和更新的数据集大小匹配
     *
     * @throws Exception
     */
    @Test
    public void displayMarvelHeroes() throws Exception {
        MainActivity activity = mockActivity();
        List<ContactInfo> infos = ContactsHelper.mockData();
        activity.displayMarvelHeroes(infos);
        TabLayout tab = activity.findViewById(R.id.avatarTab);
        assertEquals(infos.size(), tab.getTabCount());
        VerticalViewPager detail = activity.findViewById(R.id.detailList);
        assertEquals(infos.size(), detail.getAdapter().getCount());
    }

    /**
     * 设置 ViewPager，测试 Tab 是否移动到对应位置
     *
     * @throws Exception
     */
    @Test
    public void testClickTab() throws Exception {
        MainActivity activity = mockActivity();
        List<ContactInfo> infos = ContactsHelper.mockData();
        activity.displayMarvelHeroes(infos);
        VerticalViewPager detail = activity.findViewById(R.id.detailList);
        int selected = 1;
        detail.setCurrentItem(selected);
        TabLayout tab = activity.findViewById(R.id.avatarTab);
        assertEquals(selected, tab.getSelectedTabPosition());
    }

    /**
     * 调用 showProgress 后是否显示了进度条
     *
     * @throws Exception
     */
    @Test
    public void showProgress() throws Exception {
        MainActivity activity = mockActivity();
        activity.showProgress();
        assertEquals(View.VISIBLE, activity.findViewById(R.id.progressBar));
    }

    /**
     * 调用 hideProgress 后是否隐藏了进度条
     *
     * @throws Exception
     */
    @Test
    public void hideProgress() throws Exception {
        MainActivity activity = mockActivity();
        activity.hideProgress();
        assertEquals(View.GONE, activity.findViewById(R.id.progressBar));
    }
}