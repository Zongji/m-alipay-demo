package com.demo.mvc.utils;

import org.thymeleaf.util.DateUtils;

import java.util.Date;

public class IdUtils {
    public static String getSeq(String prefix) {
        return prefix + System.currentTimeMillis() + "";
    }
}
