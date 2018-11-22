package com.yongf.android.contactsapp.domain.interactor;

import com.yongf.android.contactsapp.domain.executor.base.Executor;
import com.yongf.android.contactsapp.domain.executor.base.MainThread;
import com.yongf.android.contactsapp.domain.model.ContactInfo;
import com.yongf.android.contactsapp.domain.repository.MarvelRepository;

import java.util.List;

/**
 * 获取漫威英雄列表的 interactor
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public class GetMarvelHeroes extends AbstractInteractor {

    private final Callback mCallback;
    private final MarvelRepository mRepository;

    public GetMarvelHeroes(Executor jobExecutor, MainThread mainThread,
                           Callback callback, MarvelRepository repository) {
        super(jobExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        List<ContactInfo> marvelData = mRepository.getMarvelData();

        // 初步检查数据是否合法
        if (marvelData == null || marvelData.size() <= 0) {
            notifyError();
            return;
        }

        // 分发数据
        dispatch(marvelData);
    }

    private void dispatch(final List<ContactInfo> heroes) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onHeroesRetrieved(heroes);
            }
        });
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("OOPS, something is wrong -_-||");
            }
        });
    }

    public interface Callback {
        /**
         * 获取数据成功的回调
         *
         * @param heroes 漫威英雄列表
         */
        void onHeroesRetrieved(List<ContactInfo> heroes);

        /**
         * 获取数据失败的回调
         *
         * @param error 错误描述
         */
        void onRetrievalFailed(String error);
    }
}
