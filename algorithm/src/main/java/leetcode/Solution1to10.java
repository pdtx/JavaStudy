/**
 * @description: 1-10题
 * @author: fancying
 * @create: 2019-03-05 21:21
 **/
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution1to10 {

    /**
     * 1:
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int ans = target - nums[i];
            if (map.containsKey(ans)){
                return new int []{map.get(ans), i};
            }
            map.put(nums[i],i);
        }

        throw new IllegalArgumentException("NO SOLUTION");
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 示例 1:
     *
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc"
     *
     * 注意：循环完之后还有做一次反转 char 转 string 用String.valueOf() ,不要用toString
     * 数组没有重载 toString 方法 所有最后的结果为类型@哈希值
     * */
    public String reverseWords(String s) {
        char [] ss = s.toCharArray();
        int start = 0;
        for(int i = 0; i < ss.length; i++) {
            if(ss[i] == ' ') {
                reverseChar(ss, start, i-1);
                start = i + 1;
            }
        }
        if (start < ss.length-1)
            reverseChar(ss, start, ss.length-1);
        return String.valueOf(ss);
    }
    private void reverseChar(char s[], int start ,int end) {
        while (start < end) {
            char ch = s[start];
            s[start++] = s[end];
            s[end--] = ch;
        }
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        Solution1to10 solution = new Solution1to10();
        System.out.println(solution.reverseWords(s));
    }

}