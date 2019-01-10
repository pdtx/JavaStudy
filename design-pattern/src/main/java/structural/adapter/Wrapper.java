/**
 * @description: 对象的适配器模式
 * @author: fancying
 * @create: 2019-01-10 21:21
 **/
package structural.adapter;

/**
 * 对象的适配器模式
 * 基本思路和类的适配器模式相同，只是将Adapter类作修改，
 * 这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题
*/
public class Wrapper implements Target{
    private Source source;

    public Wrapper(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the Target method!");
    }
}