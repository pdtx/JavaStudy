/**
 * @description: 静态代理类
 * @author: fancying
 * @create: 2019-02-27 21:31
 **/
package structural.proxy.statical;

import structural.proxy.Hello;
import structural.proxy.HelloInterface;

public class HelloProxyStatic implements HelloInterface {

    private HelloInterface helloInterface = new Hello();

    @Override
    public void sayHello() {
        System.out.println("before say hello");
        helloInterface.sayHello();
        System.out.println("after say hello");
    }
}