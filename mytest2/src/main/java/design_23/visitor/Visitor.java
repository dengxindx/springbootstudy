package design_23.visitor;

/**
 * 存放要访问的对象
 */
public interface Visitor {

    void visit(Subject sub);
}
