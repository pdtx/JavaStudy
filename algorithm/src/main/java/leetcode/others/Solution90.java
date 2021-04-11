package leetcode.others;


import java.util.*;
import java.util.stream.Collectors;

public class Solution90 {

    public static void main(String[] args) {
        int[] nums = { 1,2 };
        System.out.println(subsetsWithDup(nums));

    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> res = new ArrayList<>((int)Math.pow(2,nums.length));
        res.add(new ArrayList<>(0));

        for (int n : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> list : res) {
                List<Integer> integerList = new ArrayList<>(list);
                integerList.add(n);
                tmp.add(integerList);
            }
            res.addAll(tmp);
        }

        return new ArrayList<>(res.stream().collect(Collectors.groupingBy( list -> {
            Collections.sort(list);
            return new ArrayList<>(list);})).keySet());
    }
}