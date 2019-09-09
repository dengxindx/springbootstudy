package design_23.memento;

public class Test {

    public static void main(String[] args) {
        Original original = new Original("hello");

        Storage storage = new Storage(original.createMemento());

        System.out.println("初始状态：" + original.getValue());
        // 修改值
        original.setValue("hi");
        System.out.println("修改后的状态：" + original.getValue());

        // 恢复备份
        original.restoreMemento(storage.getMemento());
        System.out.println("恢复后的状态为：" + original.getValue());
    }
}
