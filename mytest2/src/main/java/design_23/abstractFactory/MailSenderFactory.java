package design_23.abstractFactory;

public class MailSenderFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
