/**
 * @description: ThreadLocal 价值说明示例，CopyValueIntoEveryThread
 * @author: fancying
 * @create: 2019-01-01 15:39
 **/
package chapter7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * ThreadLocal 通常用于同一个线程内，跨类、跨方法传递数据
 * ThreadLocal是一个本地线程副本变量工具类
 * 主要用于将私有线程和该线程存放的副本对象做一个映射，各个线程之间的变量互不干扰，
 * 在高并发场景下，可以实现无状态的调用，特别适用于各个线程依赖不通的变量值完成操作的场景.
 * 每个ThreadLocal只能保存一个变量副本，如果想要上线一个线程能够保存多个副本以上，就需要创建多个ThreadLocal
 * */
public class CsGameByThreadLocal {
    /**
     *  final修饰的方法表示该方法在子类中不能被重写，final修饰的类表示该类不能被继承
     * */

    private static final Integer BULLET_NUMBER = 1500;
    private static final Integer KILLED_ENEMIES = 0;
    private static final Integer LIFE_VALUE = 10;
    private static final Integer TOTAL_PLAYERS = 10;
    // 随机数用来展示每个对象的不同的数据
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private static final ThreadLocal<Integer> BULLET_NUMBER_THREADLOCAL = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue(){
            return BULLET_NUMBER;
        }
    };

    private static final ThreadLocal<Integer> KILLED_ENEMIES_THREADLOCAL =  ThreadLocal.withInitial(new Supplier<Integer>() {
        @Override
        public Integer get() {
            return KILLED_ENEMIES;
        }
    });
    // lambda 表达式写法
    private static final ThreadLocal<Integer> LIFE_VALUE_THREADLOCAL =  ThreadLocal.withInitial( () -> KILLED_ENEMIES );

    private static class Player extends Thread {

        @Override
        public void run() {
            Integer bullets = BULLET_NUMBER_THREADLOCAL.get() - RANDOM.nextInt(BULLET_NUMBER);
            Integer killEnemies = KILLED_ENEMIES_THREADLOCAL.get() - RANDOM.nextInt(TOTAL_PLAYERS / 2);
            Integer lifeValue = LIFE_VALUE_THREADLOCAL.get() - RANDOM.nextInt(LIFE_VALUE);

            System.out.println(getName() + ", BULLET_NUMBER is " + bullets);
            System.out.println(getName() + ", KILLED_ENEMIES is " + killEnemies);
            System.out.println(getName() + ", LIFE_VALUE is " + lifeValue);

            // 如果没有 remove 操作，容易引起内存泄漏
            // ThreadLocalMap的key是弱引用，而Value是强引用
            // ThreadLocal在没有外部对象强引用时，发生GC时弱引用Key会被回收，而Value不会回收
            BULLET_NUMBER_THREADLOCAL.remove();
            KILLED_ENEMIES_THREADLOCAL.remove();
            LIFE_VALUE_THREADLOCAL.remove();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_PLAYERS; i++) {
            new Player().start();
        }
    }
}