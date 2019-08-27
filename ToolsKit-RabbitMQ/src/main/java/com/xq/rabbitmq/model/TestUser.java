package com.xq.rabbitmq.model;

import lombok.Data;

@Data
public class TestUser {
    int age;

    public TestUser(){}
    public TestUser(int age){this.age= age;}
}
