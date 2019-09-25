package com.company.mobile;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class MobileController extends Controller {

    public void index() {
        List<Record> records = Db.find("select * from mobile limit ?", 10);
        renderJson(records);
    }

    public void list() {
        List<Record> records = Db.template("mobile.list", getInt(0,3)).find();
        renderJson(records);
    }

    public void find() {
        Kv cond = Kv.by("name", "济南").set("code", "0531");
        Page<Record> paginate = Db.template("mobile.find_by_city", cond).paginate(1, 10);
        renderJson(paginate);
    }
}
