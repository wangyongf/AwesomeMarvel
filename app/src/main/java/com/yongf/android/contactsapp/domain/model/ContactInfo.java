package com.yongf.android.contactsapp.domain.model;

import android.support.annotation.DrawableRes;

/**
 * 联系人信息模型类
 *
 * @author scottwang1996@qq.com
 * @since 2018/11/21.
 */
public class ContactInfo {

    private String firstName;
    private String lastName;
    private String title;               // 职位/头衔
    private String desc;                // 生平事迹
    private @DrawableRes
    int avatarResId;           // 头像资源

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(@DrawableRes int avatarResId) {
        this.avatarResId = avatarResId;
    }
}
