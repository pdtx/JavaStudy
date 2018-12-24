/**
 * @description: 让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行
 * @author: fancying
 * @create: 2018-12-24 21:47
 **/
package chapter8;

import java.util.concurrent.CyclicBarrier;
/**
 *  可用于多线程计算数据，最后合并计算结果的场景
 *  CountDownLatch 的计数器只能使用一次，而CyclicBarrier 的计数器可以使用 reset() 方法重置
 * */
public class CyclicBarrierTest {
    // （parties，barrierAction）  线程在到达屏障时 优先执行barrierAction
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread( () -> {
            try {
                cyclicBarrier.await();
            }catch (Exception e){
            }
            System.out.println(1);
        }).start();

        try {
            cyclicBarrier.await();
        }catch (Exception e){
        }
        System.out.println(2);
    }
}

class A implements Runnable {

    @Override
    public void run() {
        System.out.println(3);
    }
}