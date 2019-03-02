package com.zym.memorymasterserver.controller;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by 12390 on 2019/3/2.
 */
@IocBean
public class BookInformationController {
    @Inject
    Dao dao;


}
