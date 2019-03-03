/**
 * @description: 代理模式测试
 * @author: fancying
 * @create: 2019-02-27 21:26
 **/
package structural.proxy;


import net.sf.cglib.proxy.Enhancer;
import structural.proxy.dynamic.HelloProxyCglib;
import structural.proxy.dynamic.HelloProxyJDK;
import structural.proxy.statical.HelloProxyStatic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {

        //静态代理测试
        HelloProxyStatic helloProxyStatic = new HelloProxyStatic();
        helloProxyStatic.sayHello();


        //JDK 动态代理测试
        // 在这里指定被代理类
        Hello hello = new Hello();
        InvocationHandler invocationHandler = new HelloProxyJDK(hello);
        // 以下是一次性生成代理
        HelloInterface helloInterface = (HelloInterface) Proxy.newProxyInstance(
                invocationHandler.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                invocationHandler);
        helloInterface.sayHello();


        //cglib 动态代理测试

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new HelloProxyCglib());
        Hello hello1 = (Hello)enhancer.create();
        hello1.sayHello();
    }
}