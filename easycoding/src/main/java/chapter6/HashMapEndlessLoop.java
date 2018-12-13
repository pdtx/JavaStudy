/**
 * @description: HashMap 死链问题示例
 * @author: fancying
 * @create: 2018-12-13 21:49
 **/
package chapter6;

import java.util.HashMap;

public class HashMapEndlessLoop {
    private static HashMap<Long, EasyCoding> map = new HashMap<Long, EasyCoding>();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            (new Thread() {
                public void run() {
                    map.put(System.nanoTime(), new EasyCoding());
                }
            }).start();
        }

        (new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(System.nanoTime(), new EasyCoding());
            }
        })).start();

    }

}

class EasyCoding {}