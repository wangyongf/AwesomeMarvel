package com.yongf.android.contactsapp.domain.executor.impl;

import com.yongf.android.contactsapp.domain.executor.base.Executor;
import com.yongf.android.contactsapp.domain.interactor.AbstractInteractor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池，执行各种耗时任务
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/20.
 */
public class JobExecutor implements Executor {

    private static final int CORE_POOL_SIZE = 3;            // 当空闲时，最多保留 3 个线程
    private static final int MAX_POOL_SIZE = 5;             // 最多 5 个线程同时存在
    private static final int KEEP_ALIVE_TIME = 120;              // 120s，空闲线程最多存活 2 分钟
    private static final int CAPACITY = 16;

    private static volatile JobExecutor INSTANCE;
    private ExecutorService mExecutor;

    private JobExecutor() {
        mExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(CAPACITY),
                Executors.defaultThreadFactory());
    }

    public static JobExecutor getInstance() {
        if (INSTANCE == null) {
            synchronized (JobExecutor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JobExecutor();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * execute a task. ThreadPoolExecutor.execute
     *
     * @param interactor 待执行的 interactor
     */
    @Override
    public void execute(final AbstractInteractor interactor) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 开始执行此任务
                    interactor.run();
                } catch (Exception e) {
                    // no-op by default.
                }

                // 标记任务已执行完毕
                interactor.onFinish();
            }
        });
    }
}
