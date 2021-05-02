package leetcode.others;

//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,2,3,2]
//输出：3
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 104
// -231 <= nums[i] <= 231 - 1
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
//
//
//
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
// Related Topics 位运算
// 👍 582 👎 0
import org.junit.Assert;
import org.junit.Test;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution137 {

    public static void main(String[] args) {

    }


    /**
     * des: 哈希表解法的空间复杂度是 O(n)的，而题目的【进阶】部分提到应当使用常数空间来做。
     *
     * 其中一个比较容易想到的做法，是利用 int 类型固定为 32 位。
     *
     * 使用一个长度为 32 的数组 cnt[] 记录下所有数值的每一位共出现了多少次 11，再对 cnt[] 数组的每一位进行 mod 33 操作，重新拼凑出只出现一次的数值。
     *
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((cnt[i] % 3 & 1) == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }

    @Test
    public void testM(){
        long start = System.currentTimeMillis();
        Assert.assertEquals(500 ,singleNumber(new int[] {30000,500,100,30000,100,30000,100}));
        Assert.assertEquals(3, singleNumber(new int[] {3}));
        Assert.assertEquals(3, singleNumber(new int[] {2,3,2,2}));
        Assert.assertEquals(3, singleNumber(new int[] {2,2,3,2}));
        Assert.assertEquals(99, singleNumber(new int [] {99,1,3,1,1,3,3}));
        Assert.assertEquals(99, singleNumber(new int [] {0,1,0,1,0,1,99}));


        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}