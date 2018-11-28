/**
 * @description: 覆写demo 实例
 * @author: fancying
 * @create: 2018-11-28 22:46
 **/
package cn.edu.fudan.fancying.javastudy.java8;

public class Father {
    protected void doSomeThing(){
        System.out.println("Father");
        this.doSomeThing();
    }

    public static void main(String[] args){
        Father father = new Son();
        //向上转型父类执行子类的方法 doSomeThing
        father.doSomeThing();
    }

}

class Son extends Father{
    @Override
    public void doSomeThing(){
        System.out.println("son");
        super.doSomeThing();
    }
}