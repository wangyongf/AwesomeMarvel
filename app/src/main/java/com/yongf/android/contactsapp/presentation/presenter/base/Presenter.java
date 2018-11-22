package com.yongf.android.contactsapp.presentation.presenter.base;

import android.app.Activity;

/**
 * Presenter 与 UI 的生命周期绑定
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public interface Presenter {

    /**
     * 对应于 {@link Activity#onResume()}
     */
    void resume();

    /**
     * 对应于 {@link Activity#onPause()}
     */
    void pause();

    /**
     * 对应于 {@link Activity#onStop()}
     */
    void stop();

    /**
     * 对应于 {@link Activity#onDestroy()}
     */
    void destroy();

    /**
     * 加载数据时使用
     */
    void onLoading();

    /**
     * 出现异常时的回调，比如数据加载失败，通知 UI
     *
     * @param error 出现的异常
     */
    void onError(Error error);
}
