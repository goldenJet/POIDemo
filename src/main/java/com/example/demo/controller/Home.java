package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jet.chen on 2017/7/7.
 */
@Controller
public class Home {

    @RequestMapping("/home")
    public String home(){
        return "uploadAndDownload";
    }
}
