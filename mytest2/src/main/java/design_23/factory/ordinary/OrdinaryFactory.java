package design_23.factory.ordinary;

public class OrdinaryFactory {
    public Sender produce(String type){
        if ("mail".equals(type))
            return new MailSender();

        else
            return null;
    }
}
