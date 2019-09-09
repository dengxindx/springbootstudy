package design_23.iterator;

public class Iterator_Test {

    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();

        Iterator_My iterator = myCollection.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
