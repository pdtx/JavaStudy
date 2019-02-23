/**
 * @description: 模板方法的抽象类
 * @author: fancying
 * @create: 2019-02-09 14:54
 **/
package behavioral.template;

public abstract class CaffeineBeverage {

    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();

    }

    protected abstract void brew();

    protected abstract void addCondiments();

    void boilWater() {
        System.out.println("boiling water!");
    }

    void pourInCup() {
        System.out.println("pour water in cup");
    }
}