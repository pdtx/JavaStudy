package leetcode.others;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 1249 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 20:20
 **/
public class Solution46 {


    public static void main(String[] args) {
        int[] nums = {0,1};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        List<List<Integer>> res = new LinkedList<>();
        List<Integer> r1 = new LinkedList<>();
        r1.add(0,nums[0]);
        res.add(r1);

        for (int i = 1 ; i< nums.length ; i++) {
            List<List<Integer>> tmpRes = new ArrayList<>();
            for (List<Integer> order : res) {
                for (int pos = 1; pos <= order.size() ; pos++) {
                    List<Integer> list = new LinkedList(order);
                    list.add(pos, i);
                    tmpRes.add(list);
                }
                order.add(0,i);
            }
            res.addAll(tmpRes);
        }
        return res;
    }

}
