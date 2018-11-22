package com.yongf.android.contactsapp.domain.executor.base;

import com.yongf.android.contactsapp.domain.interactor.AbstractInteractor;

/**
 * 任务执行者（线程池）
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public interface Executor {

    /**
     * 执行耗时操作
     *
     * @param interactor 待执行的 interactor
     */
    void execute(AbstractInteractor interactor);
}
