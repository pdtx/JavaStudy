/**
 * @description: 异常使用示例
 * @author: fancying
 * @create: 2018-12-08 12:45
 **/
package chapter5;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *  相对在finally 代码块中赋值，更加危险的做法是在finally 块中使用return 操作
 *  finally 是在return 表达式运行后执行的
 * */
public class TryCatchFinally {

    /**
     * 在try代码块之前调用lock() 方法，避免由于加锁失败导致finally调用unlock() 抛出异常
     * finally 用于处理善后清理工作（清理资源、释放链接、关闭管道流）
     * */
    public void tryDemo(){
        Lock lock = new Lock() {
            @Override
            public void lock() {

            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {

            }

            @Override
            public Condition newCondition() {
                return null;
            }
        };

        try {
            // 无论加锁是否成功， unlock 都会执行
            lock.lock();
        }finally {
            lock.unlock();
        }

    }

}