package design_23.visitor;

public interface Subject {
    void accept(Visitor visitor);
    String getSubject();
}
