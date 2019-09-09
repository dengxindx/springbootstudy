package design_23.interpreter;

public class TEST {
    public static void main(String[] args) {
        Context context = new Context(5, 2);

        Plus plus = new Plus();
        int interpret = plus.interpret(context);

        Minus minus = new Minus();
        int interpret1 = minus.interpret(context);

        System.out.println("plus:" + interpret);
        System.out.println("minus:" + interpret1);
    }
}
