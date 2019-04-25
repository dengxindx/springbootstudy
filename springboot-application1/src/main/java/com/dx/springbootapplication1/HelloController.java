package com.dx.springbootapplication1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/hi")
    public String sayHello(){
        return "Hello World.";
    }
}
