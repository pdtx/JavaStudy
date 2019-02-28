/**
 * @description: JDK 动态代理
 * @author: fancying
 * @create: 2019-02-28 22:07
 **/
package structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloProxyJDK implements InvocationHandler {

    private Object object;

    public HelloProxyJDK() {}

    public HelloProxyJDK(Object o) {
        object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before jdk dynamic proxy ");
        method.invoke(object, args);
        System.out.println("after jdk dynamic proxy");
        return null;
    }
}