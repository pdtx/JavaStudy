/**
 * @description: 数据类型
 * @author: fancying
 * @create: 2018-11-29 21:00
 **/
package chapter2;




/**
* @Description: （1）所有POJO类属性必须使用包装数据类型
 * （2）RPC方法的返回值和参数必须使用包装类型
 * （3）所有的局部变量推荐使用基本数据类型
 *
 * Integer 是唯一可以修改的缓存范围的包装类
 * -XX：AutoBoxCacheMax=7777
*/
public class TypeOfData {

    public static void main(String[] args) {
        // Long 类型只缓存 -128 -- 127 之间的数值
        Long a = 127L;
        Long b = 127L;
        System.out.println(a == b);

        Long a1 = 128L;
        Long b1 = 128L;
        System.out.println(a1 == b1);

        Integer x = 1000;
        Integer y = 1000;
        System.out.println(x == y);

    }
}