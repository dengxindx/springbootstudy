package com.dx.springbootrabbitmqtest2.headers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

//    @Autowired
//    @Qualifier("headersSender")
    @Resource(name = "headersSender")
    private Sender sender;

    @Test
    public void send() throws Exception {
        sender.send();
        sender.send2();
        sender.send3();
    }
}