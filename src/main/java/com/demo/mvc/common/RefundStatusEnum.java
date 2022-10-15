package com.demo.mvc.common;

public enum RefundStatusEnum {
    CREATE("已提交"),
    REFUND("已退款"),
    PAID_TIMEOUT("退款超时"),
    PAID_FAIL("退款失败"),
    ;

    private String desc;
    RefundStatusEnum(String desc) {
        this.desc = desc;
    }

}
