package com.company;

import com.company.home.HomeController;
import com.company.mobile.MobileController;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(MainConfig.class);
    }

    @Override
    public void configConstant(Constants me) {}

    @Override
    public void configRoute(Routes me) {
        me.add("/", HomeController.class);
        me.add("/mobile", MobileController.class);
    }

    @Override
    public void configEngine(Engine me) {}

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(
                "jdbc:mysql://192.168.113.128:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false",
                "root",
                "123456",
                "com.mysql.cj.jdbc.Driver");
        me.add(dp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setDialect(new MysqlDialect());
//        _MappingKit.mapping(arp);
        arp.addSqlTemplate("sql/mobile.sql");
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {}

    @Override
    public void configHandler(Handlers me) {}
}
