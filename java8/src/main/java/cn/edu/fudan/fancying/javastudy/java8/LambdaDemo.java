/**
 * @description: Lambda表达式基础语法, Java8中引入新的操作符"->
 * @author: fancying
 * @create: 2018-11-06 17:14
 **/
package cn.edu.fudan.fancying.javastudy.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaDemo {

    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        r.run();
        System.out.println("------");

        //lambda
        Runnable runnable = () -> System.out.println("hello,Lambda");
        runnable.run();
    }

    @Test public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override public void accept(String t) {
                System.out.println(t);
            }
        };
        consumer.accept("hello consumer !");
        System.out.println("---------------");
        //Lambda表达式
        // Consumer<String> con = (t) -> System.out.println(t);
        Consumer<String> con = System.out::println;
        con.accept("hello Lambda !");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("相加的结果是：" +(x+y));
            return Integer.compare(x,y);
        };

        int compare = comparator.compare(1,2);
        System.out.println(compare);

        //Comparator<Integer> comparator1 = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> comparator1 = Integer::compare;
        //Comparator.comparing(Integer::intValue);
        compare = comparator1.compare(1,2);
        System.out.println(compare);

        Comparator<Integer> comparator2 = (x,y) -> Integer.compare(x,y);
        compare = comparator2.compare(1,2);
        System.out.println(compare);

    }

    @Test public void test11(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("shuai");
        //方法引用,对象::实例方法名
        Consumer<String> consumer = System.out::println;
        consumer.accept("test");
    }

    //类::静态方法名
    @Test public void test33(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
        //使用前提,compare的参数和返回值与Comparator一致
        Comparator<Integer> comparator = Integer :: compare;
    }
    // 类::实例方法名
    @Test public void test4(){

        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        //使用条件:第一个参数是实例方法调用者,第二个参数是实例方法的参数
        BiPredicate<String, String> biPredicate = String :: equals;
    }

    //数组引用
    @Test public void test7() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(8);
        System.out.println(strs.length);

        Function<Integer, String[]> function = String[] :: new;
        String[] strArray = function.apply(6);
        System.out.println(strArray[0]);
        System.out.println(strArray.length);
    }
}