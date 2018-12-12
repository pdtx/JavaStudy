/**
 * @description: TreeMap demo
 * @author: fancying
 * @create: 2018-12-12 22:45
 **/
package chapter6;

import java.util.HashMap;
import java.util.TreeMap;

public class TreeMapRepeat {


    /**
     * HashMap 是使用hashCode 和equals 实现去重
     * 而 TreeMap 依靠Comparable或者 Comparator 来实现key的去重
     * */
    public static void main(String[] args) {
        // 结果为2
        TreeMap map = new TreeMap();
        map.put(new Key(),"value one");
        map.put(new Key(),"value two");
        System.out.println(map.size());

        // 结果为1
        HashMap map1 = new HashMap();
        map1.put(new Key(),"value one");
        map1.put(new Key(),"value two");
        System.out.println(map1.size());

    }
}

class Key implements Comparable<Key> {

    @Override
    // 返回负的常数 ，表示此对象永远小于输入的other 对象
    public int compareTo(Key other) {
        return -1;
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}