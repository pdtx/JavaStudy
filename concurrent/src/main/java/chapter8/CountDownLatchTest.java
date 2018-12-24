/**
 * @description: CountDownLatch 允许一个或多个线程等待其他线程完成操作
 * @author: fancying
 * @create: 2018-12-24 21:36
 **/
package chapter8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{
        new Thread(() -> {
            System.out.println(1);
            countDownLatch.countDown();
            System.out.println(2);
            countDownLatch.countDown();
        } ).start();

        countDownLatch.await();
        System.out.println(3);
    }
}