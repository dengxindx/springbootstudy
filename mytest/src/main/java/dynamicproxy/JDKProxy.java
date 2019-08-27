package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler{

    private Object target;

    public Object obtainInstance(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                (Class<?>[])target.getClass().getGenericInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        System.out.println("jdk动态代理开始执行");
        method.invoke(target, args);
        System.out.println("jdk动态代理结束");
        long end = System.currentTimeMillis();
        System.out.println("jdk动态代理执行时长：" + (end - start) + "毫秒");
        return null;
    }
}
