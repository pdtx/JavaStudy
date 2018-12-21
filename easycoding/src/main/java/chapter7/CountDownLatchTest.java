/**
 * @description: 1：实现最大的并行性；2：开始执行前等待n个线程完成各自任务；3：死锁检测
 * @author: fancying
 * @create: 2018-12-21 21:06
 **/
package chapter7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        countDownLatchTest.execute();
    }

    void execute() {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Thread thread1 = new TranslateThread("1a",countDownLatch);
        Thread thread2 = new TranslateThread("2a", countDownLatch);
        Thread thread3 = new TranslateThread("3a", countDownLatch);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("all complete!");
    }

    class TranslateThread extends Thread {
        private  String content;
        private  final CountDownLatch countDownLatch;

        TranslateThread(String content,CountDownLatch countDownLatch) {
            this.content = content;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            if (Math.random() > 0.5) {
                throw new RuntimeException("原文存在非法字符");
            }
            System.out.println(content + "翻译已完成");
            countDownLatch.countDown();
        }
    }
}