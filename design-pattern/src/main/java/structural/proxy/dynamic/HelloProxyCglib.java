/**
 * @description: cglib 动态代理
 * @author: fancying
 * @create: 2019-03-03 21:39
 **/
package structural.proxy.dynamic;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloProxyCglib implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before cglib dynamic proxy ");
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("after cglib dynamic proxy");
        return object;
    }
}