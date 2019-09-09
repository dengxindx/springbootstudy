package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 */
public class JDKPro {
    static Object obtInstance(Object target){
        // 代理方法
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                (Class<?>[]) target.getClass().getGenericInterfaces(),
                (proxy, method, args) -> {
                    long start = System.currentTimeMillis();
                    System.out.println("jdk动态代理开始执行");

                    Object invoke = method.invoke(target, args);

                    System.out.println("jdk动态代理结束");
                    long end = System.currentTimeMillis();
                    System.out.println("jdk动态代理执行时长：" + (end - start) + "毫秒");

                    if (invoke != null)
                        return invoke;
                    return null;
                }
        );
    }
}
