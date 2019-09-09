package design_23.observer;

public class ObserverTest {

    public static void main(String[] args) {
        Subject mySubject = new MySubject();

        Observer1 observer1 = new Observer1();
        Observer2 observer2 = new Observer2();

        mySubject.add(observer1);
        mySubject.add(observer2);

        mySubject.operation();
        mySubject.del(observer2);

        System.out.println("========================");

        mySubject.operation();
    }
}
