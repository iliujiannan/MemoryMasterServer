package com.zym.memorymasterserver.controller;

import com.zym.memorymasterserver.bean.BookInformation;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.util.List;

/**
 * Created by 12390 on 2019/3/2.
 */
@IocBean
public class BookInformationController {
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
                re.put("books", booksList);
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
    public Object getOneBook(@Param("bookInformationId") String bookId){
        NutMap re=new NutMap();
        BookInformation b=dao.fetch(BookInformation.class, Cnd.where("bookInformationId","=",Integer.valueOf(bookId)));
        re.put("status",1);
        re.put("msg","OK");
        re.put("book",b);
        return re;
    }
}
