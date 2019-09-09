package design_23.adapter.interfaceadapter;

public class SourceSub1 extends Wrapper2{
    @Override
    public void method1() {
        System.out.println("the sourceable interface's first Sub1!");
    }

    public static void main(String[] args) {
        Sourceable source1 = new SourceSub1();
        source1.method1();
        source1.method2();
    }
}
