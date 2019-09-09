package test;

public class Task_1 implements Sub{
    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public String t(String a) {
        System.out.println("t");
        return a;
    }
}
