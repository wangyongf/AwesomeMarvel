package com.yongf.android.contactsapp.presentation.presenter.impl;

import com.yongf.android.contactsapp.domain.executor.base.Executor;
import com.yongf.android.contactsapp.domain.executor.base.MainThread;
import com.yongf.android.contactsapp.domain.executor.impl.JobExecutor;
import com.yongf.android.contactsapp.domain.executor.impl.MainThreadImpl;
import com.yongf.android.contactsapp.domain.interactor.GetMarvelHeroes;
import com.yongf.android.contactsapp.domain.model.ContactInfo;
import com.yongf.android.contactsapp.domain.repository.MarvelRepository;
import com.yongf.android.contactsapp.presentation.presenter.base.AbstractPresenter;
import com.yongf.android.contactsapp.presentation.view.base.BaseView;

import java.util.List;

/**
 * Presenter for MainActivity, contains many business logic
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public class MainPresenter extends AbstractPresenter implements GetMarvelHeroes.Callback {

    private final View mView;
    private GetMarvelHeroes mGetMarvelHeroes;

    public MainPresenter(Executor jobExecutor, MainThread mainThread,
                         View view, MarvelRepository repository) {
        super(jobExecutor, mainThread);

        mGetMarvelHeroes = new GetMarvelHeroes(JobExecutor.getInstance(),
                MainThreadImpl.getInstance(), this, repository);
        mView = view;
    }

    @Override
    public void resume() {
        // 显示 Loading
        mView.showProgress();
        // 开始加载超级英雄列表数据
        mGetMarvelHeroes.execute();
    }

    @Override
    public void onHeroesRetrieved(List<ContactInfo> heroes) {
        mView.hideProgress();
        mView.displayMarvelHeroes(heroes);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        mView.showError(error);
    }

    public interface View extends BaseView {
        /**
         * 渲染超级英雄列表
         *
         * @param infos 数据列表
         */
        void displayMarvelHeroes(List<ContactInfo> infos);
    }
}
