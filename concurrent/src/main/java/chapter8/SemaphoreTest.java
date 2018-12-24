/**
 * @description: 控制同时访问特定资源的线程数量
 * @author: fancying
 * @create: 2018-12-24 22:06
 **/
package chapter8;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 可以用于做流量控制，特别是共用资源有限的应用场景，比如数据库连接
 *  */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;

    private static Executor executorThreadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorThreadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("save data");
                    semaphore.release();
                }catch (InterruptedException e){

                }
            });
        }

    }

}