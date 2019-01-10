/**
 * @description: 类的适配器模式
 * @author: fancying
 * @create: 2019-01-10 21:09
 **/
package structural.adapter;

/**
* 核心思想就是：有一个Source类，拥有一个方法，待适配，
 * 目标接口是 Target，通过Adapter类，将Source的功能扩展到Target里
*/
public class AdapterTest {

    public static void main(String[] args) {
        Target target = new Adapter();
        target.method1();
        target.method2();

        Source source = new Source();
        Target target1 = new Wrapper(source);
        target1.method1();
        target1.method2();
    }
}

class Source {

    public void method1() {
        System.out.println("this is original method!");
    }
}

interface Target {

    // 与原类中的方法相同
    public void method1();

    // 新类的方法
    public void method2();
}

/**
* Adapter类继承Source类，实现Target接口
*/
class Adapter extends Source implements Target {

    @Override
    public void method2() {
        System.out.println("this is the Target method!");
    }
}
