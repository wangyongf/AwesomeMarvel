package com.yongf.android.contactsapp.domain.executor.impl;

import android.os.Handler;
import android.os.Looper;

import com.yongf.android.contactsapp.domain.executor.base.MainThread;

/**
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public class MainThreadImpl implements MainThread {

    private static volatile MainThreadImpl INSTANCE;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static MainThreadImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (MainThreadImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainThreadImpl();
                }
            }
        }
        return INSTANCE;
    }

    private Handler mHandler;

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
