package com.zym.memorymasterserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by 23381 on 2018/8/17.
 */
@Table("xrr_check_code")
public class Checkcode {
    @Id
    private Integer checkCodeId;
    @Column("phone")
    private String phone;
    @Column("check_code")
    private String checkCode;
    @Column("last_time")
    private String lastTime;


    public Integer getCheckCodeId() {
        return checkCodeId;
    }

    public void setCheckCodeId(Integer checkCodeId) {
        this.checkCodeId = checkCodeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
