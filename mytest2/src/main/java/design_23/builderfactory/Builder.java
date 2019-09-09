package design_23.builderfactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 */
public class Builder {
    public static void main(String[] args) {
        /* 测试生成 */
        Builder builder = new Builder();
        builder.produceMailSender(10);
        builder.produceSmsSender(10);
    }

    private List<Sender> list = new ArrayList<>();

    public void produceMailSender(int count){
        for(int i=0; i<count; i++){
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count){
        for(int i=0; i<count; i++){
            list.add(new SmsSender());
        }
    }

}
