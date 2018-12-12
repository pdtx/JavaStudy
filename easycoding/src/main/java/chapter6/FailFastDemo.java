/**
 * @description: fail-fast 机制
 * @author: fancying
 * @create: 2018-12-12 11:27
 **/
package chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastDemo {


    public static void main(String[] args) {
 //       subListFailFast();
        copyOnWriteDemo();
    }
    /**
     *  使用COW 时应注意两点：第一，尽量设置合理的容量初始值，它的扩容代价比较大
     *  第二：使用批量添加或删除方法，如addAll或removeAll操作，在高并发请求下，可以攒一下要添加或者删除的元素
     * */
    private static void copyOnWriteDemo() {
        List<Long> copy = new CopyOnWriteArrayList<Long>();
        Long start = System.nanoTime();
        for (int i=0; i<20*10000;i++) {
            copy.add(System.nanoTime());
        }
    }

    /**
     * Description: subList 子列表无法序列化，它的修改会导致主列表的修改
     *   subList 返回的是内部类 SubList 的对象，并没 有实现序列化接口，无法网络传输
     * return: void
     * Date: 2018/12/12
     */
    private static void subListFailFast() {
        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");

        List branchList = masterList.subList(0,2);

        // 下面三行代码，如果不注释掉，则会导致 branchList 操作出现异常
//        masterList.remove(0);
//        masterList.add("ten");
//        masterList.clear();

        branchList.clear();
        branchList.add("five");
        branchList.add("six");
        branchList.remove(0);

        for (Object t : branchList) {
            System.out.println(t);
        }

        System.out.println(masterList);
    }


}