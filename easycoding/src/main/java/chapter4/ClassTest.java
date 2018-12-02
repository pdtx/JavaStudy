/**
 * @description: 全小写的class是关键字，用来定义类，而首字母大写的Class是所有的类
 * @author: fancying
 * @create: 2018-12-02 20:33
 **/
package chapter4;

import java.lang.reflect.Field;

public class ClassTest {
    // 数组类型有一个魔法属性：length 来获取数组长度
    private static int[] array = new int[3];
    private static int length = array.length;

    // 任何小写 class 定义的类，也有一个魔法属性：Class，来获取此类的大写Class类对象
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;


    /**
     * new 是强类型校验，可以调用任何构造方法，在使用new 操作的时候，这个类可以没有被加载过
     *
     *
     * 在类加载的时候，jvm会创建一个class对象
     *
     * class对象是可以说是反射中最常用的，获取class对象的方式的主要有三种
     *
     *     根据类名：类名.class
     *     根据对象：对象.getClass()
     *     根据全限定类名：Class.forName(全限定类名)
     *
     * */
    public static void main(String[] args) throws Exception{

        // 通过 newInstance 方法创建One 和 Another 的类对象
        // jdk9 中 newInstance 已经过时，使用getDeclaredConstructor().newInstance()的方式
        One oneObject = one.newInstance();
        oneObject.call();

        Another anotherObject = another.newInstance();
        anotherObject.speack();

        Field privateFieldInOne = one.getDeclaredField("inner");

        privateFieldInOne.setAccessible(true);

        privateFieldInOne.set(oneObject,"changed");

        System.out.println(oneObject.getInner());
    }

}

class One {
    private String inner = "time flies.";

    public void call() {
        System.out.println("hello world");
    }

    public String getInner() {
        return inner;
    }
}

class Another {
    public void speack() {
        System.out.println("easy coding.");
    }
}