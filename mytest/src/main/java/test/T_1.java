package test;

import dynamicproxy.JDKProxy;

public class T_1 {
    public static void main(String[] args) {
        Sub task_1 = new Task_1();
        Sub o = (Sub)JDKPro.obtInstance(task_1);
        o.show();
        String t = o.t("hi");
        System.out.println(t);
    }
}
