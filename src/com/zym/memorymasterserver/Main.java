package com.zym.memorymasterserver;

/**
 * Created by 12390 on 2017/9/22.
 */
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;

import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
        "*anno", "com.zym.memorymasterserver",
        "*tx",
        "*async"})

@IocBean
@Modules(scanPackage=true)
public class Main {

}
