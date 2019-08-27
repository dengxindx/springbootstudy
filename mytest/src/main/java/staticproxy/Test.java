package staticproxy;

public class Test {
    public static void main(String[] args) {
        Subject subject = new MyProxy();
        subject.show();
    }
}
