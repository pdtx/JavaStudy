/**
 * @description:
 * @author: fancying
 * @create: 2019-02-09 15:02
 **/
package behavioral.template;

public class Coffee extends CaffeineBeverage {
    @Override
    protected void brew() {
        System.out.println("stepping coffee");
    }

    @Override
    protected void addCondiments() {
        System.out.println("add sugar and milk");
    }
}