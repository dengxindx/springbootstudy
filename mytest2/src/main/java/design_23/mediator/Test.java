package design_23.mediator;

public class Test {
    public static void main(String[] args) {
        MyMediator myMediator = new MyMediator();
        myMediator.createMediator();
        myMediator.workAll();
    }
}
