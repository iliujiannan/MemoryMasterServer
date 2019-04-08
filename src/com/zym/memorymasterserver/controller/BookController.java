package com.zym.memorymasterserver.controller;

import com.zym.memorymasterserver.bean.BookContent;
import com.zym.memorymasterserver.bean.BookInformation;
import com.zym.memorymasterserver.bean.Purse;
import com.zym.memorymasterserver.bean.Users;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.impl.entity.NutEntity;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/3/2.
 */
@IocBean
public class BookController {
    @Inject
    Dao dao;

    @Ok("json")
    @Fail("http:500")
    @At("get_all_books")
    @GET
    @POST
    public Object getAllBooks(@Param("bookType") String bookType){
        NutMap re=new NutMap();
        List<BookInformation> booksList;
        if(bookType.equals("0")){
            booksList=dao.query(BookInformation.class, Cnd.orderBy().asc("bookInformationId"));
        }else{
            booksList = dao.query(BookInformation.class, Cnd.where("book_type", "=", bookType).asc("bookInformationId"));
        }
        if(booksList!=null) {
            if(booksList.size()>0) {
                re.put("status", 1);
                re.put("msg", "OK");
                re.put("bookInformations", booksList);
                return re;
            }
        }
        re.put("status", 0);
        re.put("msg", "没有数据");
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("get_one_book")
    @GET
    @POST
    public Object getOneBookDetail(@Param("bookInformationId") String bookId){
        NutMap re=new NutMap();
        BookInformation b=dao.fetch(BookInformation.class, Cnd.where("bookInformationId","=",Integer.valueOf(bookId)));
        re.put("status",1);
        re.put("msg","OK");
        re.put("bookInformation",b);
        return re;
    }

    @Ok("json")
    @Fail("http:500")
    @At("add_book")
    @GET
    @POST
    public Object addBook(@Param("userId") String userId, @Param("secretKey") String secretKey,
                          @Param("flag")Integer flag, @Param("bookInformationId") String bookId){
        NutMap re = new NutMap();
        if(userId!=null&&secretKey!=null){
            Users u=dao.fetch(Users.class, Cnd.where("userId","=",Integer.valueOf(userId)));
            if(secretKey.equals(u.getSecretKey())){
                BookInformation b=dao.fetch(BookInformation.class,
                        Cnd.where("bookInformationId","=",Integer.valueOf(bookId)));
                Sql sql = Sqls.create("select * from $table;");
                sql.vars().set("table", b.getBookPath());
                sql.setCallback(new SqlCallback() {
                    @Override
                    public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                        List<BookContent> list = new Vector<>();
                        while(resultSet.next()){
                            BookContent bookContent = new BookContent();
                            bookContent.setBookContentId(resultSet.getInt("book_content_id"));
                            bookContent.setContentA(resultSet.getString("content_a"));
                            bookContent.setContentQ(resultSet.getString("content_q"));
                            bookContent.setContentHint(resultSet.getString("content_hint"));
                            bookContent.setIsOkOnEve(resultSet.getInt("is_ok_on_eve"));
                            bookContent.setIsOkOnMorning(resultSet.getInt("is_ok_on_morning"));
                            bookContent.setRememberAmount(resultSet.getInt("remember_amount"));
                            bookContent.setRootChapter(resultSet.getInt("root_chapter"));
                            bookContent.setRootBookInformationId(resultSet.getInt("root_book_information_id"));
                            bookContent.setStartRememberTime(resultSet.getString("start_remember_time"));
                            list.add(bookContent);
                        }
                        return list;
                    }
                });
                dao.execute(sql);
                List<BookContent> list = sql.getList(BookContent.class);
                re.put("bookContents", list);
                re.put("status",1);
                re.put("msg","OK");
            }
            else {
                re.put("status",0);
                re.put("msg","您还未登陆！");
            }
        }
        else {
            re.put("status",0);
            re.put("msg","您还未登陆！");
        }
        return re;
    }

}
