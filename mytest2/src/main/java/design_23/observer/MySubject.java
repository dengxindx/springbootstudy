package design_23.observer;

public class MySubject extends AbstractSubject{
    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }
}
