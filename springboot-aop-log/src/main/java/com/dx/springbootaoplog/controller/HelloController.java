package com.dx.springbootaoplog.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(name = "name", required = true) String name){
        return "Hello " + name;
    }
}
