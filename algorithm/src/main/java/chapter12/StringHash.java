/**
 * @description: 字符串 hash
 * @author: fancying
 * @create: 2018-12-06 17:22
 **/
package chapter12;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StringHash {

    private static int uuidToInt(String uuid){
        String id = uuid.replace("-","");


        return 0;
    }

    public static void main(String[] args) {

        int same = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++ ){
            uuidToInt(UUID.randomUUID().toString());

        }

    }

}