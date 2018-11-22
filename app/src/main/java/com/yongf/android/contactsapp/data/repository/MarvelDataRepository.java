package com.yongf.android.contactsapp.data.repository;

import android.os.SystemClock;

import com.yongf.android.contactsapp.domain.model.ContactInfo;
import com.yongf.android.contactsapp.domain.repository.MarvelRepository;
import com.yongf.android.contactsapp.util.ContactsHelper;

import java.util.List;

/**
 * 真正地获取漫威超级英雄列表
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public class MarvelDataRepository implements MarvelRepository {

    @Override
    public List<ContactInfo> getMarvelData() {
        SystemClock.sleep(2000);            // 模拟网络延迟

        return ContactsHelper.mockData();
    }
}
