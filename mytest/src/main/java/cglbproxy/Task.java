package cglbproxy;

import java.util.Random;

public class Task {
    public void show(){
        try {
            System.out.println("cglib执行任务....");
            Thread.sleep(new Random().nextInt(5000));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
