package com.tools.rabbitmq;

import lombok.Data;

@Data
public class User {
    int age;
    String name;

    public User() {

    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
