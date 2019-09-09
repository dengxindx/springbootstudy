package design_23.decorator;

//被装饰类
// ps 感觉有点代理?
public class Source implements Sourceable{

    @Override
    public void method() {
        System.out.println("the original method!");
    }

    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }
}
