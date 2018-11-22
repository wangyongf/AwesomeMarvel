package com.yongf.android.contactsapp.presentation.presenter.base;

import com.yongf.android.contactsapp.domain.executor.base.Executor;
import com.yongf.android.contactsapp.domain.executor.base.MainThread;

/**
 * 所有 Presenter 的基类，定义一系列公共方法、属性
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public class AbstractPresenter implements Presenter {

    protected Executor mJobExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor jobExecutor, MainThread mainThread) {
        mJobExecutor = jobExecutor;
        mMainThread = mainThread;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onError(Error error) {

    }
}
