/**
 * @description: 基于双重检查锁定来实现延迟初始化的方案
 * @author: fancying
 * @create: 2018-12-17 22:18
 **/
package chapter3;

/**
 * 基于volatile的解决方案
 * 适合 对实例字段使用线程安全的延迟初始化
 * */
public class SafeDoubleCheckedLocking {
    private volatile static Instance instance;

    public static Instance getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}



/**
 * 基于类的初始化方案
 * 适合 对静态字段使用线程安全的延迟初始化
 * */

class InstanceFactory {
    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        // 这里将导致InstanceHolder类被初始化
        return InstanceHolder.instance;
    }
}

class Instance {

}