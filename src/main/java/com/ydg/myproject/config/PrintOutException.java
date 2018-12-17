package com.ydg.myproject.config;

import com.alibaba.fastjson.JSONObject;
import com.ydg.myproject.exception.BizException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ydg
 * @date 2018/8/23
 * @description
 */
public class PrintOutException {
    public static void printException(HttpServletResponse res, BizException e) {
        JSONObject json = new JSONObject();
        json.put("resultCode", e.getCode());
        json.put("resultMsg", e.getMessage());
        json.put("data", "");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        PrintWriter pw = null;
        try {
            pw = res.getWriter();
        } catch (IOException ep) {
            ep.printStackTrace();
        }
        pw.write(json.toJSONString());
    }
}
