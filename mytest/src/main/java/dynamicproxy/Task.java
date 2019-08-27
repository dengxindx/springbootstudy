package dynamicproxy;

import java.util.Random;

public class Task implements Subject{
    @Override
    public void show(){
        try {
            System.out.println("执行任务....");
            Thread.sleep(new Random().nextInt(5000));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
