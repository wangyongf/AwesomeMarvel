package com.yongf.android.contactsapp.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yongf.android.contactsapp.R;
import com.yongf.android.contactsapp.data.repository.MarvelDataRepository;
import com.yongf.android.contactsapp.domain.executor.impl.JobExecutor;
import com.yongf.android.contactsapp.domain.executor.impl.MainThreadImpl;
import com.yongf.android.contactsapp.domain.model.ContactInfo;
import com.yongf.android.contactsapp.presentation.presenter.impl.MainPresenter;
import com.yongf.android.contactsapp.widget.RoundedImageView;
import com.yongf.android.contactsapp.widget.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private ProgressBar mProgressBar;
    private TabLayout mAvatarTab;
    private VerticalViewPager mDetailList;
    private DetailAdapter mDetailAdapter;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);

        initUI();
        initPresenter();
    }

    private void initUI() {
        mProgressBar = findViewById(R.id.progressBar);
        mAvatarTab = findViewById(R.id.avatarTab);
        mDetailList = findViewById(R.id.detailList);

        mDetailAdapter = new DetailAdapter(null);
        mDetailList.setAdapter(mDetailAdapter);

        buildTab();
    }

    private void buildTab() {
        mAvatarTab.setupWithViewPager(mDetailList);
        mAvatarTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getCustomView() == null) {
                    return;
                }
                ((RoundedImageView) tab.getCustomView().findViewById(R.id.ivAvatar)).setIsSelected(true);
                mDetailList.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getCustomView() == null) {
                    return;
                }
                ((RoundedImageView) tab.getCustomView().findViewById(R.id.ivAvatar)).setIsSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initPresenter() {
        mPresenter = new MainPresenter(JobExecutor.getInstance(),
                MainThreadImpl.getInstance(), this, new MarvelDataRepository());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.resume();
    }

    @Override
    public void displayMarvelHeroes(List<ContactInfo> infos) {
        mDetailAdapter.update(infos);
        updateAvatarTab(infos);
    }

    /**
     * 更新头像 Tab
     *
     * @param infos
     */
    private void updateAvatarTab(List<ContactInfo> infos) {
        for (int i = 0; i < infos.size(); i++) {
            TabLayout.Tab tab = mAvatarTab.getTabAt(i);
            tab.setCustomView(R.layout.app_item_avatar);
            RoundedImageView avatar = tab.getCustomView().findViewById(R.id.ivAvatar);
            if (i == 0) {
                avatar.setIsSelected(true);
                tab.select();
            } else {
                avatar.setIsSelected(false);
            }
            avatar.setImageResource(infos.get(i).getAvatarResId());
        }
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    class DetailAdapter extends PagerAdapter {

        private List<ContactInfo> mInfos;

        DetailAdapter(@NonNull List<ContactInfo> infos) {
            if (infos != null) {
                mInfos = infos;
            } else {
                mInfos = new ArrayList<>();
            }
        }

        public void update(@NonNull List<ContactInfo> list) {
            if (list == null) {
                return;
            }
            mInfos.clear();
            mInfos.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mInfos.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.app_item_detail, container, false);
            ((TextView) view.findViewById(R.id.tvFirstName)).setText(mInfos.get(position).getFirstName());
            ((TextView) view.findViewById(R.id.tvLastName)).setText(mInfos.get(position).getLastName());
            ((TextView) view.findViewById(R.id.tvTitle)).setText(mInfos.get(position).getTitle());
            ((TextView) view.findViewById(R.id.tvDescBody)).setText(mInfos.get(position).getDesc());

            container.addView(view);
            return view;
        }
    }
}
