package com.demo.mvc.config;

import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2021000119683733";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCZolbxqsGW08YPuWcrYKhfn69gnubQq+hS12HdsxKjJpAkpmjHTTUGawRFdz8PjeEnumqF539IhduJm+V6A81LmhmcyOBjYEijyq1HRNsLEHbQfqJTvV+pKDqTsSkxosVZfuPGvV1tVuSh3B3d2WCIRimUAFs8ZskeaQTLVU0aEHOlm5PaWq2Z5lEmOmI99QTlwr6W8aVLuL9OvHAdjygbsNL3B/48AEaBmXZVVG7Cown37XULyjtiwHc7Xjc6DtCMLkC078C+USUXnJ1c/O4ZtkOQ5YmXl3EayjqzQfT+0Bile6I6vurq1S8KsCZmnHA+rSPf65O0AEbpcNhYRZcNAgMBAAECggEABjnuDB0/ez1Z6Yk6BSgFqtpt4RyPfiV5ZOIVtUZsmvAVJ2cXF/ypCXmM0sPial71oQaNCqA3KFlEbG9E/Yzp5QZ5w0Dg1yYvy3OLUjgYKHqiVhDLw1kJZ3GZ7iJf6QMPvHDICmXiQ2wiF+0zyKnmn584UCczi18fB3x6FUN/vG6d8leIxPSCMNE2TT04uQK6e4k4QXkBVjvIsmUz+vlGVcquxn2zHLXAx2mlqkq0MOMwycQ47kHyvelI8RgWqAXfmy96NUqGSiFVh1aywX1jUBUknaz0RJe9LjNfhad6kkIbG/xlc1rRgtJZKNxkHYfxOpMknF9vCkF5Q7jS1hsWgQKBgQDrHtSkwgTGoBu3rNgYXQZViZnvdiMc/HOTARojz7vHaRFnsll8Qs06Md3afV9+hTje/phWzIrHYpP9fKtJ1BFtqCwbova5QFMAcB1DgeglI+i/g7/tMQk8BfCTZQY7pZ0YAh+YJv7R2WH7POzqRs21q7AioB+BfvFX0uJeK1laNwKBgQCnRwUXh6JSagqvNrso5HSEWLfdhO0a45iNoqfaKp7RXbSRtZmIrlIiLMzasV3zv8me7g3rHdBSFiWld6jtYz1sCqajEYRAGo8j0X/rkpXdQI3Vg74ZKsu5Iq0mb3x5Lla54d1xbSuUq0t+SiSu1J8KhjrFJruMpZZOM6gP3njm2wKBgDkEiALQfkIAz9fyFZGK9Z9GuErm9krwOcLbkJBAr0QDidIA4UyOEqDbOPbkUoUnH4yMZzsdbUY+9K6TTiKgR/20u8WT9oskOzyu85nMZhSjqazP8Xo+2wNwWVAVPra2SetpkGnggwk201kPBFEoDHjTAurSs/E+bJ6kqHq3vnpvAoGAMuutoyIIyam6Iug4RRR2W6teHTLPsrXBxKvVCNacjgNGamyrli3qlrioi5IelYq2Z4jz97SVq8dOInawl8Lz+aWsYEt+xLzRAghONnni4P07r9lwztx2M59YsjCR5ZmzkNYuwM6Iy7LxQjpzvDzBRUTjMwy9Xu0xOhEDggEpMK0CgYB5PgPSRn8LNXM+QC7rWny8i2jWuYebTPEtgEDQQ+cQS0ZGR8CuTNZnC6O8Bvr2htNyVHhpTQyTClhudAFTzpjt7ZY3raNGQjYT9JSz2YHYVziKcqrD3lm5GHPYITJP3azMjf1e0qizi6kjXhrgmVfWugSOLLRo6hnu1iB7Gv9BGw==";

    public static String APP_HOST = "https://4ce7-163-125-202-196.jp.ngrok.io";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问http://192.168.2.100:8080/alipay.trade.wap.pay-java-utf-8/
    public static String notify_url = APP_HOST + "/pay/pay-notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
//    public static String return_url = "http://192.168.10.8:8080/pay-result"; //前台页面地址
    public static String return_url = APP_HOST + "/pay/pay-return"; //后台页面地址

    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo1mL+VyyI7c7djoZIYSe1NI15AsLkMcjl8ZuMvMNgu82QlpMNteyE7PdamJOQMjBuG7m4mOm+vHIW+XR0xOimJkZCrV8qWJhyOSfLZvs761Kf3BKmTrw6pWmTnOaavGeeHdL77EmjbOjFxXU7biljkeIuwLRI4rdT1rhgtKMVEPka2L/Ooo3D5DL+TMQcOY3+MNoJxFe/RyQwlP7PFw/2JNH+Q4TrTQcelHRBNw0aPgfUidvZpn1ifvBaFU8DvpRtOd/mxVolqKqiTxhJ0fBl9TDHLWyZF8E7EoU8wjxIIQZeVFbhd0WhBWTQdcfpQLnBzCc+wJNoYcAyS2BInJRRwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";

    public static String AES_KEY = "lN/wsLNGiNUC6A6KgN+k5A==";
}
