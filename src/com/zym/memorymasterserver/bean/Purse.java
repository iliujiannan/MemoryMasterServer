package com.zym.memorymasterserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 23381 on 2018/8/16.
 */

@Table("mm_purse")
public class Purse {
    @Id
    private Integer purseId;
    @Column("user_id")
    private Integer userId;
    @Column("purse_money")
    private Integer purseMoney;

    public Integer getPurseId() {
        return purseId;
    }

    public void setPurseId(Integer purseId) {
        this.purseId = purseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPurseMoney() {
        return purseMoney;
    }

    public void setPurseMoney(Integer purseMoney) {
        this.purseMoney = purseMoney;
    }
}
