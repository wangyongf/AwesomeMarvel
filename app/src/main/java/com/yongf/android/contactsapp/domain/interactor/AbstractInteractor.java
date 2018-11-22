package com.yongf.android.contactsapp.domain.interactor;

import com.yongf.android.contactsapp.domain.executor.base.Executor;
import com.yongf.android.contactsapp.domain.executor.base.MainThread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 所有 interactor 的基类，定义一系列公共方法、属性
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public abstract class AbstractInteractor implements Interactor {

    protected Executor mJobExecutor;
    protected MainThread mMainThread;

    protected AtomicBoolean mIsCanceled = new AtomicBoolean(false);
    protected AtomicBoolean mIsRunning = new AtomicBoolean(false);

    public AbstractInteractor(Executor jobExecutor, MainThread mainThread) {
        mJobExecutor = jobExecutor;
        mMainThread = mainThread;
    }

    /**
     * 实际执行的业务逻辑，这部分逻辑会放在后台线程（池）中执行
     */
    public abstract void run();

    /**
     * 取消 interactor 的执行，置标志位
     */
    public void cancel() {
        mIsCanceled.set(true);
        mIsRunning.set(false);
    }

    public boolean isRunning() {
        return mIsRunning.get();
    }

    public boolean isCanceled() {
        return mIsCanceled.get();
    }

    public void onFinish() {
        mIsRunning.set(false);
        mIsCanceled.set(false);
    }

    @Override
    public void execute() {
        mIsRunning.set(true);

        // 将这个任务加入到线程池中
        mJobExecutor.execute(this);
    }
}
