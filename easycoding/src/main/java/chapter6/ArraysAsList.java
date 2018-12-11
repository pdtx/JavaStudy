/**
 * @description: 数据转集合示例
 * @author: fancying
 * @create: 2018-12-11 16:13
 **/
package chapter6;

import java.util.Arrays;
import java.util.List;

/**
* @Description:  Arrays.asList 是适配器模式，后台的数据仍是原有数组
 *                 asList 的返回对象是一个Arrays 的内部类，他没有实现集合个数的相关修改方法
* @Date: 2018/12/11
*/
public class ArraysAsList {

    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[3] = "three";

        List<String> stringList = Arrays.asList(stringArray);

        //推荐
        List<String> stringList1 = new java.util.ArrayList<String>(stringList);
        stringList.set(0,"oneList");
        System.out.println(stringArray[0]);

        // 下面三行编译正确，单都会抛出运行时异常
        stringList.add("four");
        stringList.remove(2);
        stringList.clear();
    }


}