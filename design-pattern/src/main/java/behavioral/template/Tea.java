/**
 * @description:
 * @author: fancying
 * @create: 2019-02-09 15:01
 **/
package behavioral.template;

public class Tea extends CaffeineBeverage {
    @Override
    protected void brew() {
        System.out.println("stepping tea");
    }

    @Override
    protected void addCondiments() {
        System.out.println("add lemon");
    }
}