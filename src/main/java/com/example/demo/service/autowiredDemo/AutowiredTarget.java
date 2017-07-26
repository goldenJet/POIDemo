package com.example.demo.service.autowiredDemo;

import com.example.demo.entity.People;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jet.chen on 2017/7/26.
 */
@Service
public class AutowiredTarget {

    public final String target = "target";

    public AutowiredTarget() {
        System.out.println("AutowiredTarget  无参构造被调用！");
    }

    //@Autowired
    public AutowiredTarget(People people) {
        System.out.println("AutowiredTarget  有参构造被调用  1！");
    }

    @Autowired
    public AutowiredTarget(Person person) {
        System.out.println("AutowiredTarget  有参构造被调用  2！");
    }
}
