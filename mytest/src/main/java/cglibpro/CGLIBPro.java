package cglibpro;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBPro implements MethodInterceptor{
    private Object target;

    public Object obtain(Object target){
        this.target = target;

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib动态代理开始执行");

        Object invoke = method.invoke(target, objects);

        Object o1 = methodProxy.invokeSuper(o, objects);

        System.out.println("cglib动态代理结束");

        if (o1 != null)
            return o1;

//        if (invoke != null)
//            return invoke;
        return null;
    }
}
