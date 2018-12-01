/**
 * @description: 类
 * @author: fancying
 * @create: 2018-11-30 11:13
 **/
package chapter2;

public class ClassDemo {

    public static void main(String[] args){
        Father father = new Son();
        //向上转型父类执行子类的方法 doSomeThing
        father.doSomeThing();
    }

}

class Father {
    protected void doSomeThing(){
        System.out.println("Father");
        this.doSomeThing();
    }
}

class Son extends Father{
    @Override
    public void doSomeThing(){
        System.out.println("son");
        super.doSomeThing();
    }
}