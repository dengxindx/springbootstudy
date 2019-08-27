package staticproxy;

public class MyProxy implements Subject{
    private Task task = new Task();

    @Override
    public void show() {
        long start = System.currentTimeMillis();
        task.show();
        long end = System.currentTimeMillis();
        System.out.println("执行时长：" + (end - start) + "毫秒");
    }
}
