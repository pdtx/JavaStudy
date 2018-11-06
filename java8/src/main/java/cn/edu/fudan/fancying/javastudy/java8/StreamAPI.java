/**
 * @description: Stream api demo
 * @author: fancying
 * @create: 2018-11-06 21:35
 **/
package cn.edu.fudan.fancying.javastudy.java8;

import org.junit.Test;

import java.util.Optional;

public class StreamAPI {

    public static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }
    }
    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        // return if(text != null) ? text.length() : -1 ;
    }

    public  void op{
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        getLength(strA);
        getLength("");
        getLength(strB);
    }

}