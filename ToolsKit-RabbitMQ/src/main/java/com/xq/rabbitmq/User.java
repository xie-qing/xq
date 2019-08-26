package com.xq.rabbitmq;

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
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
