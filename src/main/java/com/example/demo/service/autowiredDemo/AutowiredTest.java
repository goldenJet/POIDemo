package com.example.demo.service.autowiredDemo;

import com.example.demo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jet.chen on 2017/7/26.
 */
@Service
public class AutowiredTest {

    @Autowired
    People people;

    public AutowiredTest() {
        //people.setName("Skye");
    }
}
