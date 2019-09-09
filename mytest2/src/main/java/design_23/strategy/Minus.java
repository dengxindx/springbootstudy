package design_23.strategy;

public class Minus extends AbstractCalculator implements ICalculator{

    /**
     * 按照-分割，实现加法
     * @param exp
     * @return
     */
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp,"\\-");
        return arrayInt[0] - arrayInt[1];
    }
}
