package design_23.iterator;

public interface Iterator_My {
    //前移
    Object previous();

    //后移
    Object next();

    //是否含有下个元素
    boolean hasNext();

    //取得第一个元素
    Object first();
}
