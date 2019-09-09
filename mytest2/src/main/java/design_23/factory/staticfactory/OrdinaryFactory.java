package design_23.factory.staticfactory;

public class OrdinaryFactory {
    public static Sender produceMail(String type){
        return new MailSender();
    }
}
