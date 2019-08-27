package dynamicproxy;

public class Test {
    public static void main(String[] args) {
        Subject target = new Task();
        Subject o = (Subject)new JDKProxy().obtainInstance(target);
        o.show();
    }
}
