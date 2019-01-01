/**
 * @description: Executors 线程工厂 拒绝策略示例
 * @author: fancying
 * @create: 2018-12-28 22:45
 **/
package chapter7;

public class UserThreadPool {

    public static void main(String[] args) {
        int c = Integer.SIZE -3 ;

        int m = (1 << c)  ;
        int n = m -1;
        System.out.println(m);
        System.out.println(Integer.toBinaryString(m));
        System.out.println(Integer.toBinaryString(n));
        // int 32 位 1 左移动 1000 的结果就是 1 左移 8
        System.out.println(1 << 8);
    }
}