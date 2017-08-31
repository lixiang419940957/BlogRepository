package com.lix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/blog")
@Controller
public class BloggerController {
    
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "hello12";
    }

}
