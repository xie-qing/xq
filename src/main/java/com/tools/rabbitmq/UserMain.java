package com.tools.rabbitmq;

import org.springframework.beans.BeanUtils;

public class UserMain {

    public static void main(String[] args){
        // 属性复制
        User user = new User("张三");
        TestUser testUser = new TestUser(15);
        // 属性复制testUser要复制的数据源   user复制目标结果
        BeanUtils.copyProperties(testUser , user);
        System.out.println(user.toString());
    }
}
