package design_23.iterator;

public class MyCollection implements Collection_My {

    public String string[] = {"A","B","C","D","E"};

    @Override
    public Iterator_My iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return string[i];
    }

    @Override
    public int size() {
        return string.length;
    }
}
