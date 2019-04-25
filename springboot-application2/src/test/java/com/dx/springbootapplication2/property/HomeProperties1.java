package com.dx.springbootapplication2.property;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeProperties1 {

    @Autowired
    HomeProperties homeProperties;

    @Test
    public void t1(){
        System.out.println("homeProperties = " + homeProperties);
    }
}
