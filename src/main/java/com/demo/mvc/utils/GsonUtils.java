package com.demo.mvc.utils;

import com.google.gson.Gson;

public class GsonUtils {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
