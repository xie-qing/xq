package com.tools.rabbitmq;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    int age;
    String name;

    public User() {

    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
