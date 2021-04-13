package leetcode.others;


import java.util.*;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution179 {

    public static void main(String[] args) {

        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        int end = a.indexOf("1");
        a = a.subList(0,end);

        System.out.println("8".compareTo("87"));
        System.out.println("87".compareTo("89"));
        System.out.println("8".compareTo("80"));
        System.out.println("8".compareTo("85"));



        int[] nums = {3,30,34,5,9};
        // 9534330
        System.out.println(largestNumber(nums));
    }

    public static String largestNumber(int[] nums) {

        StringBuilder stringBuilder = new StringBuilder();
        Integer[] integers = new Integer[nums.length];
        for (int i = 0; i< nums.length ; i++) {
            integers[i] = nums[i];
        }
        Arrays.sort(integers, (x,y) -> (String.valueOf(y)+ x).compareTo(String.valueOf(x)+y));

        if (integers[0] == 0) {
            return "0";
        }

        Arrays.stream(integers).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

}