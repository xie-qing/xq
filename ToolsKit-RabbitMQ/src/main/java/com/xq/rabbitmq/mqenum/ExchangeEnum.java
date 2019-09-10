package com.xq.rabbitmq.mqenum;

import lombok.Data;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/9/10 16:34
 */
public enum ExchangeEnum {

    /**
     * 用户注册交换配置枚举
     */
    USER_REGISTER("user.register.topic.exchange");
    private String value;

    ExchangeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
