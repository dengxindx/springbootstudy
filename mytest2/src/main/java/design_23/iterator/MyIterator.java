package design_23.iterator;

public class MyIterator implements Iterator_My{

    private Collection_My collection_my;
    int pos = -1;

    public MyIterator(Collection_My collection_my) {
        this.collection_my = collection_my;
    }

    @Override
    public Object previous() {
        if (pos > 0)
            pos--;
        return collection_my.get(pos);
    }

    @Override
    public Object next() {
        if (pos < collection_my.size() - 1)
            pos++;
        return collection_my.get(pos);
    }

    @Override
    public boolean hasNext() {
        return pos < collection_my.size() - 1;
    }

    @Override
    public Object first() {
        pos = 0; // 偏移指针
        return collection_my.get(pos);
    }
}
