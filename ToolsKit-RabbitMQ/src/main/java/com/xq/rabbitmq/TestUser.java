package com.xq.rabbitmq;

import lombok.Data;

@Data
public class TestUser {
    int age;

    public TestUser(){}
    public TestUser(int age){this.age= age;}
}
