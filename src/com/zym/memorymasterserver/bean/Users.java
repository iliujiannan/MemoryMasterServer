package com.zym.memorymasterserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */

@Table("mm_users")
public class Users {
    @Id
    private Integer userId;
    @Column("user_phone")
    private String userPhone;
    @Column("user_photo")
    @Default("img/sys_photo.jpg")
    private String userPhoto;
    @Column("user_nickname")
    private String userNickName;
    @Column("psw")
    private String psw;
    @Column("secret_key")
    private String secretKey;
    @Column("user_curr_book_id")
    @Default("-1")
    private Integer userCurrentBookId;
    @Default("0")
    @Column("user_completed_days")
    private Integer userCompletedDays;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getUserCurrentBookId() {
        return userCurrentBookId;
    }

    public void setUserCurrentBookId(Integer userCurrentBookId) {
        this.userCurrentBookId = userCurrentBookId;
    }

    public Integer getUserCompletedDays() {
        return userCompletedDays;
    }

    public void setUserCompletedDays(Integer userCompletedDays) {
        this.userCompletedDays = userCompletedDays;
    }
}
