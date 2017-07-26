package com.example.demo.entity;

import org.springframework.stereotype.Component;

/**
 * Created by Jet.chen on 2017/7/26.
 */
@Component
public class People {

    public static final String father = "FATHER";

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
