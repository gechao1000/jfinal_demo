package com.company.home;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HomeValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        setRet(Ret.fail());
        setShortCircuit(true);

        validateRequired(0, "msg", "日期不能为空");
        try {
            LocalDate date = LocalDate.parse(c.get(0), DateTimeFormatter.BASIC_ISO_DATE);
            if (date.isAfter(LocalDate.now())) {
                addError("msg", "日期不能大于今天");
            }
            c.set("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } catch (DateTimeParseException e) {
            addError("msg", "日期格式错误");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson(getRet());
    }
}
