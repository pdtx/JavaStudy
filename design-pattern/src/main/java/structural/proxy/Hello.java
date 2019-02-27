/**
 * @description: HelloInterface 的实现
 * @author: fancying
 * @create: 2019-02-27 21:30
 **/
package structural.proxy;

public class Hello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}