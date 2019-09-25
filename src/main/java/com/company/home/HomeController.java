package com.company.home;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.log.Log;

public class HomeController extends Controller {

    private static Log log = Log.getLog(HomeController.class);

    public void index() {
        log.debug("---------debug--------");
        log.info("---------info--------");
        log.error("---------error--------");
        log.warn("---------warn--------");
        renderText("@index");
    }

    @Before(HomeValidator.class)
    public void check() {
        String date = getAttrForStr("date");
        renderJson(Ret.ok("date", date));
    }

    public void file() {
        renderFile("Lgbao_KPMX_DownLoad_E00300012001_20190920120940.xls");
    }

    public void qrcode() {
//        String data = "http://www.baidu.com";
        String data = "qwe123";
        renderQrCode(data, 200, 200);
    }

    public void captcha() {
        // 只能使用session验证
        renderCaptcha();
    }

}
