package cglibpro;

import cglbproxy.CglibProxy;

public class T_1 {
    public static void main(String[] args) {
        SubT subT = new SubT();
        SubT subT1 = (SubT) new CGLIBPro().obtain(subT);

        subT1.show();

        String t = subT1.t("----");
        System.out.println(t);
    }
}
