/**
 * @description: 多线程基础
 * @author: fancying
 * @create: 2018-11-19 22:24
 **/
package cn.edu.fudan.fancying.javastudy.concurrent;

/**
*  并发：通过cpu调度算法，让用户看上去同时执行，实际上从cpu操作层面不是真正的同时
 *  并发往往在场景中有公用的资源，那么针对这个公用的资源往往产生瓶颈，
 *  我们会用TPS或者QPS来反应这个系统的处理能力。
*/
public class ThredDemp {


    private int product;
    private final int  MAX_PRODUCT = 999;
    private final int  MIN_PRODUCT = 9;
    /**
    * @Description: synchronized, wait, notify结合:典型场景生产者消费者问题
    * @Date: 2018/11/19
    */
    public synchronized void produce(){
        if (this.product >= MAX_PRODUCT){
            try {
                wait();
                System.out.println("产品已满");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return;
        }
        this.product++;
        System.out.println("生产者生产第" + this.product + "个产品.");
        notifyAll();   //通知等待区的消费者可以取出产品了
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void consume()
    {
        if(this.product <= MIN_PRODUCT)
        {
            try
            {
                wait();
                System.out.println("缺货,稍候再取");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("消费者取走了第" + this.product + "个产品.");
        this.product--;
        notifyAll();   //通知等待去的生产者可以生产产品了
    }

    /**
     * @Description: 多线程的内存模型：
     * main memory（主存）、working memory（线程栈），在处理数据时，线程会把值从主存load到本地栈
     * 完成操作后再save回去(volatile关键词的作用：每次针对该变量的操作都激发一次load and save)。
     * @Date: 2018/11/19
     */


}