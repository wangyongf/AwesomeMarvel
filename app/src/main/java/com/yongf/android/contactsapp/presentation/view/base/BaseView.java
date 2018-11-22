package com.yongf.android.contactsapp.presentation.view.base;

/**
 * 基类 BaseView，所有 Activity/Fragment 都必须继承之
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public interface BaseView {

    /**
     * 显示加载进度条等
     */
    void showProgress();

    /**
     * 数据加载完毕（或者出现异常），隐藏进度条
     */
    void hideProgress();

    /**
     * 数据加载出现异常，显示错误 UI
     *
     * @param message 错误信息
     */
    void showError(String message);
}
