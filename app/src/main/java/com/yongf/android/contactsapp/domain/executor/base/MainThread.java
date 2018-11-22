package com.yongf.android.contactsapp.domain.executor.base;

/**
 * 主线程
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public interface MainThread {

    /**
     * 在主线程中执行回调（更新 UI）
     *
     * @param runnable 待执行的回调
     */
    void post(Runnable runnable);
}
