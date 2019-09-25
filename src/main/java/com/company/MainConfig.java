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
    public void configConstant(Constants me) {
        // JFinal会对每次请求输出报告，如输出本次请求的URL、Controller、Method以及请求所携带的参数
        me.setDevMode(true);

        // 文件下载
        me.setBaseDownloadPath("E:/Z_Export");

        // 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
        // 注入动作支持任意深度并自动处理循环注入
        me.setInjectDependency(true);
    }

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
