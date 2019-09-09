package design_23.factory.multiple;

import design_23.factory.ordinary.Sender;

public class OrdinaryFactory {
    public Sender produceMail(){
        return new MailSender();
    }
}
