/**
 * @description: Stream api demo
 * @author: fancying
 * @create: 2018-11-06 21:35
 **/
package cn.edu.fudan.fancying.javastudy.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public  void op(){
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        getLength(strA);
        getLength("");
        getLength(strB);
    }

    public void streamAPI(){
        // 构造流的几种常见方法
        // 1. Individual values

        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        // 数值流的构造
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

    }

    /**
     * 流的操作
     *把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
     *
     *     Intermediate：
     *
     * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
     *
     *     Terminal：
     *
     * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     *
     *     Short-circuiting：
     *
     * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     *
     */

    List<Integer> nums = Arrays.asList(1, 2, 3, 4);
    List<Integer> squareNums = nums.stream().
            map(n -> n * n).
            collect(Collectors.toList());

    Stream<List<Integer>> inputStream = Stream.of(
            Arrays.asList(1),
            Arrays.asList(2, 3),
            Arrays.asList(4, 5, 6)
    );
    Stream<Integer> outputStream = inputStream.
            flatMap((childList) -> childList.stream());


}