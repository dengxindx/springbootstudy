package design_23.visitor;

public class Test {
    public static void main(String[] args) {
        Visitor visitor = new MyVisitor();
        Subject mySubject = new MySubject();
        mySubject.accept(visitor);
    }
}
