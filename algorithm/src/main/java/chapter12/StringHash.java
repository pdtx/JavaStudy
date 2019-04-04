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
        String dura = "2018.01.01-2018.12.12";
        String start = dura.substring(0,10);
        String end = dura.substring(11,21);

        System.out.println(start + " " + end);
    }

}