package cglbproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor{

    private Object target;

    public Object obtainInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
//        enhancer.setCallbackType(this.target.getClass());
        enhancer.setCallback(this);

        //  创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理开始执行");

        Object invokeSuper = methodProxy.invokeSuper(o, args);

        System.out.println("cglib动态代理结束");
        return null;
    }
}
