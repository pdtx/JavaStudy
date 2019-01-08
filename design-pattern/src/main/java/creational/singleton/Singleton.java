/**
 * @description: 单例对象（Singleton）是一种常用的设计模式。在Java应用中，单例对象能保证在一个JVM中，该对象只有一个实例存在。
 * @author: fancying
 * @create: 2019-01-08 21:23
 **/
package creational.singleton;

/**
 * 好处：1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 *      2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
 *      3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。
*/
public class Singleton {

    // 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
    // 加上volatile 可以实现线程安全的延迟初始化（禁止下文的 2 3 的重排序）
    private volatile static Singleton instance = null;

    // 私有构造方法，防止被实例化
    private Singleton() {
    }

    // 静态工厂方法，创建实例 非线程安全
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    // 静态工厂方法，创建实例
    // 线程安全,在性能上会有所下降，因为每次调用getInstance()，都要对对象上锁
    public static synchronized Singleton getInstanceSafe() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    // 双重检查锁定
    public static Singleton DoubleCheckGetInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                // 分解成三行伪代码
                // 1：分配对象的内存空间 2：初始化对象 3：设置Instance指向刚分配的内存地址
                // 2,3 重排序导致问题
                instance = new Singleton();
            }
        }
        return instance;
    }

    // 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
    public Object readResolve() {
        return instance;
    }

    /**
     * 基于类初始化的解决方案
     * JVM 在类的初始化阶段（即在 Class 被加载后，且被线程使用之前），会执行类的初始化
     * 在执行类的初始化期间，JVM 会去获取一个锁，这个锁可以同步多个线程对同一个类的初始化
    */
    private static class InstanceHolder {
        public static Singleton instance = new Singleton();
    }

    public static Singleton getInstanceBaseStaticClass() {
        return InstanceHolder.instance;
    }

}