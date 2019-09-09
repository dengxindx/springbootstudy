package design_23.strategy;

public class Test {

    public static void main(String[] args) {
        String exp1 = "8+2";
        String exp2 = "8-2";
        String exp3 = "8*2";

        ICalculator plus = new Plus();
        ICalculator minus = new Minus();
        ICalculator multiply = new Multiply();

        int result = plus.calculate(exp1);
        System.out.println(result);

        int result2 = minus.calculate(exp2);
        System.out.println(result2);

        int result3 = multiply.calculate(exp3);
        System.out.println(result3);
    }
}
