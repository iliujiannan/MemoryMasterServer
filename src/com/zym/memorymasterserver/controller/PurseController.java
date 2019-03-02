package com.zym.memorymasterserver.controller;

import com.zym.memorymasterserver.bean.Purse;
import com.zym.memorymasterserver.bean.Users;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

/**
 * Created by 12390 on 2019/3/2.
 */

@IocBean
public class PurseController {
    @Inject
    Dao dao;

    @Ok("json")
    @Fail("http:500")
    @At("charge_money")
    @GET
    @POST
    public Object chargeMoney(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("chargeMoneyAmount") String chargeMoneyAmount){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(secretKey.equals(u.getSecretKey())){
                re.put("status",1);
                re.put("msg","OK");
                Purse p=dao.fetch(Purse.class,Cnd.where("user_id","=",userId));
                p.setPurseMoney(p.getPurseMoney()+Integer.valueOf(chargeMoneyAmount));
                dao.update(p);
                re.put("chargeMoney",p.getPurseMoney());
            }
            else {
                re.put("status",0);
                re.put("msg","您无法充值！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("get_purse_money")
    @GET
    @POST
    public Object getPurseMoney(@Param("userId") String userId,@Param("secretKey") String secretKey){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(secretKey.equals(u.getSecretKey())){
                re.put("status",1);
                re.put("msg","OK");
                Purse p=dao.fetch(Purse.class,Cnd.where("user_id","=",userId));
                re.put("userMoney",p.getPurseMoney());
            }
            else {
                re.put("status",0);
                re.put("msg","您无法查看余额！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }
}
