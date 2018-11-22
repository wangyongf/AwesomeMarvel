package com.yongf.android.contactsapp.domain.repository;

import com.yongf.android.contactsapp.domain.model.ContactInfo;

import java.util.List;

/**
 * 漫威英雄数据源，domain 层的抽象类
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public interface MarvelRepository {

    /**
     * 获取漫威英雄列表数据
     *
     * @return 英雄列表，包含各种信息
     */
    List<ContactInfo> getMarvelData();
}
