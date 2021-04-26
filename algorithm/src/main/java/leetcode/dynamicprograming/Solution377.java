package leetcode.dynamicprograming;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution377 {

    public static void main(String[] args) {

    }




    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[1001];
        Arrays.fill(dp,-1);
        return combinationSum4(nums,target,dp);
    }


    public int combinationSum4(int[] nums, int target, int[] dp) {
        int diff = -1;
        int hasSelf = 0;
        int res = 0;
        for (int num : nums) {
            diff = target - num;
            // 放到候选集中
            if (diff > 0) {
                if (dp[diff] == -1) {
                    dp[diff] = combinationSum4(nums, diff, dp);
                }
                res += dp[diff];
            } else if (diff == 0) {
                hasSelf = 1;
            }
        }

        return res + hasSelf;
    }



    @Test
    public void testM(){
        Assert.assertEquals(9, combinationSum4(new int[] {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, 10));
        Assert.assertEquals(181997601, combinationSum4(new int[] {1,2,3}, 32));
        Assert.assertEquals(7, combinationSum4(new int[] {1,2,3}, 4));
        Assert.assertEquals(0, combinationSum4(new int[] {9}, 3));
    }

}