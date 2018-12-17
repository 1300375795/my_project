package com.ydg.myproject.permission;

import com.google.common.collect.Lists;
import java.util.List;

public class PermissionContextUtil {

    private static ThreadLocal<List<String>> context = new ThreadLocal<List<String>>() {
        @Override
        protected List<String> initialValue() {
            return Lists.newArrayList();
        }
    };

    public static List<String> getCurrentCodes() {
        return context.get();
    }

    public static void setCurrentCodes(List<String> codes) {
        if (codes != null) {
            context.set(codes);
        }
    }
}
