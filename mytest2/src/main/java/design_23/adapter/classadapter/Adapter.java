package design_23.adapter.classadapter;

public class Adapter extends Source implements Targetable{

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    /* 适配器方法，可重写原类方法，解决兼容性问题 */
//    @Override
//    public void method1() {
//        System.out.println("this is the targetable method!");
//    }

    /*
    适配器测试
     */
    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
