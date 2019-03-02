package com.zym.memorymasterserver.controller;

import com.zym.memorymasterserver.bean.Checkcode;
import com.zym.memorymasterserver.bean.Purse;
import com.zym.memorymasterserver.bean.Users;
import com.zym.memorymasterserver.util.DateUtil;
import com.zym.memorymasterserver.util.Message;
import com.zym.memorymasterserver.util.SecretKey;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * Created by 23381 on 2018/8/16.
 */
@IocBean
public class UserController {


    @Inject
    Dao dao;
    @Ok("json")
    @Fail("http:500")
    @At("login")
    @GET
    @POST
    public Object doLogin(@Param("userPhone")String userPhone, @Param("psw")String password){
        try{
            NutMap re = new NutMap();
            if(userPhone!=null&&password!=null) {
                Users u = dao.fetch(Users.class, Cnd.where("user_phone", "=", userPhone).and("psw", "=", password));
                if (u!=null) {
                    String secretKey = SecretKey.getSecretKey();
                    u.setSecretKey(secretKey);
                    dao.update(u);
                    re.put("status", 1);
                    re.put("msg", "OK");
                    re.put("secretKey", secretKey);
                    re.put("userId", u.getUserId());
                    re.put("currBookId", u.getUserCurrentBookId());
                    re.put("completedDays", u.getUserCompletedDays());
//                    re.put("userPhone", u.getUserPhone());
                } else {
                    re.put("status", 0);
                    re.put("msg", "账号或密码错误");
                }
            }else{
                re.put("status", 0);
                re.put("msg", "账号或密码错误");
            }
            return re;
        }catch (Exception e){
            NutMap re = new NutMap();
            re.put("status", 0);
            re.put("msg", "error in login");
            return re;
        }
    }

    @Ok("json")
    @Fail("http:500")
    @At("register")
    @GET
    @POST
    public Object register(@Param("userPhone") String userPhone, @Param("psw") String password, @Param("repsw") String rePassword) {
        NutMap re = new NutMap();
        if (userPhone != null && password != null && rePassword != null) {
            Users u = dao.fetch(Users.class, Cnd.where("user_phone", "=", userPhone));
            if (u != null) {
                re.put("status", 0);
                re.put("msg", "您已注册过，请登录！");
            } else {
                if (password.equals(rePassword)) {
                    Users user = new Users();
                    user.setUserPhone(userPhone);
                    user.setPsw(password);
                    user.setUserNickName("user_" + userPhone);
                    dao.insert(user);
                    Purse p=new Purse();
                    p.setUserId(user.getUserId());
                    p.setPurseMoney(0);
                    dao.insert(p);
                    re.put("status", 1);
                    re.put("msg", "注册成功");
                } else {
                    re.put("status", 0);
                    re.put("msg", "两次密码不一致");
                }
            }
        } else {
            re.put("status", 0);
            re.put("msg", "输入信息不合法");
        }
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("get_check_code")
    @GET
    @POST
    public Object getcheckcode(@Param("userPhone") String userPhone) {
        NutMap re = new NutMap();
        Message cc = new Message();
        Checkcode checkcode;
        String code;
        checkcode = dao.fetch(Checkcode.class, Cnd.where("phone", "=", userPhone));
        if (checkcode != null) {
            long dis = DateUtil.getDistanceTimeInMinute(checkcode.getLastTime(), DateUtil.dateToString(new Date()));
            System.out.println(dis);
            if (dis >= 1) {
                try {
                    code = cc.mSsendMessage(userPhone);
                    checkcode.setCheckCode(code);
                    checkcode.setLastTime(DateUtil.dateToString(new Date()));
                    dao.update(checkcode);
                    re.put("status", 1);
                    re.put("msg", "获取验证码成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                re.put("status", 0);
                re.put("msg", "请不要短时间内重复获取");
            }
        } else {
            try {
                code = cc.mSsendMessage(userPhone);
                checkcode = new Checkcode();
                checkcode.setCheckCode(code);
                checkcode.setPhone(userPhone);
                checkcode.setLastTime(DateUtil.dateToString(new Date()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            dao.insert(checkcode);
            re.put("status", 1);
            re.put("msg", "获取验证码成功");
        }
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("log_out")
    @GET
    @POST
    public Object logOut(@Param("userId") String userId,@Param("secretKey") String secretKey){
        NutMap re=new NutMap();
        if(secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            u.setSecretKey("");
            dao.update(u);
            re.put("status",1);
            re.put("msg","退出登录！");
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("modify_nickname")
    @GET
    @POST
    public Object modifyNickNmae(@Param("userId") String userId,@Param("secretKey") String secretKey,@Param("newNickname") String newNickname){
        NutMap re=new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(u.getSecretKey().equals(secretKey)){
                re.put("status",1);
                re.put("msg","OK");
                u.setUserNickName(newNickname);
                dao.update(u);
            }
            else{
                re.put("status",0);
                re.put("msg","您不能修改昵称");
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
    @At("modify_psw")
    @GET
    @POST
    public Object modifyPsw(@Param("userId") String userId,@Param("psw") String passWord,@Param("newpsw") String newPassWord){
        NutMap re=new NutMap();
        if(passWord!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(passWord.equals(u.getPsw())){
                u.setPsw(newPassWord);
                dao.update(u);
                re.put("status",1);
                re.put("msg","修改密码成功！");
            }
            else {
                re.put("status",0);
                re.put("msg","原密码不正确！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","原密码不能为空！");
        }
        return re;
    }


    @Ok("json")
    @Fail("http:500")
    @At("personal_information")
    @GET
    @POST
    public Object getPersonalInformation(@Param("userId") String userId,@Param("secretKey") String secretKey){
        NutMap re = new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class,Cnd.where("userId","=",Integer.valueOf(userId)).and("secret_key","=",secretKey));
            if(u!=null){
                dao.update(u);
                re.put("status",1);
                re.put("msg","Ok");
                u.setPsw("");
                re.put("userData", u);
//                re.put("userId",userId);
//                re.put("userPhone",u.getUserPhone());
//                re.put("userPhoto",u.getUserPhoto());
//                re.put("userNickName",u.getUserNickName());
//                re.put("secretKey",secretKey);
//                re.put("userType",u.getUserType());
//                re.put("userReadDailly",u.getUserReadDailly());
//                re.put("userReadTotally",u.getUserReadTotally());
            }
            else {
                re.put("status",0);
                re.put("msg","该用户信息不存在");
            }
        }
        else {
            re.put("ststus",0);
            re.put("msg","该用户信息不存在");
        }
        return re;
    }



}
