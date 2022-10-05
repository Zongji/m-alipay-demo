package com.demo.mvc.common;

public enum OrderStatusEnum {
    CREATE("已下单"),
    PAID("已支付"),
    PAID_TIMEOUT("支付超时"),
    PAID_FAIL("支付失败")
    ;

    private String desc;
    OrderStatusEnum(String desc) {
        this.desc = desc;
    }

}
