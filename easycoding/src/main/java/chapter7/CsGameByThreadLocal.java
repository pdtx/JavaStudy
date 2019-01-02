/**
 * @description: ThreadLocal 价值说明示例，CopyValueIntoEveryThread
 * @author: fancying
 * @create: 2019-01-01 15:39
 **/
package chapter7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

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
}