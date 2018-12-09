/**
 * @description: 一个例子
 * @author: fancying
 * @create: 2018-12-09 22:43
 **/
package cn.edu.fudan.fancying.javastudy.concurrent;

public class SynchronizedDemo implements Runnable{
    private static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++){
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }

        try {
            Thread.sleep(500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count:" + count);
    }

    @Override
    public void run() {
        //synchronized (SynchronizedDemo.class){
            for (int i = 0; i < 1000; i++) {
                count++;
            }
       // }


    }
}