package cglbproxy;


public class Test {
    public static void main(String[] args) {
        Task task = new Task();
        Task proxy = (Task)new CglibProxy().obtainInstance(task);
        proxy.show();
    }
}
