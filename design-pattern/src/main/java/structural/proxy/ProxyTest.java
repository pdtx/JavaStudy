/**
 * @description: 代理模式测试
 * @author: fancying
 * @create: 2019-02-27 21:26
 **/
package structural.proxy;


import structural.proxy.statical.HelloProxyStatic;

public class ProxyTest {

    public static void main(String[] args) {

        HelloProxyStatic helloProxyStatic = new HelloProxyStatic();
        helloProxyStatic.sayHello();

    }
}