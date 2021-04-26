package leetcode.others;

//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
//
//        'A' -> 1
//        'B' -> 2
//        ...
//        'Z' -> 26
//        要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
//
//        "AAJF" ，将消息分组为 (1 1 10 6)
//        "KJF" ，将消息分组为 (11 10 6)
//        注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
//
//        给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
//
//        题目数据保证答案肯定是一个 32 位 的整数。
//
//         
//
//        示例 1：
//
//        输入：s = "12"
//        输出：2
//        解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
//        示例 2：
//
//        输入：s = "226"
//        输出：3
//        解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
//        示例 3：
//
//        输入：s = "0"
//        输出：0
//        解释：没有字符映射到以 0 开头的数字。
//        含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//        由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
//        示例 4：
//
//        输入：s = "06"
//        输出：0
//        解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
//         
//
//        提示：
//
//        1 <= s.length <= 100
//        s 只包含数字，并且可能包含前导零。


import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution91 extends Solution{


    public static void main(String[] args) {
        Map<Class<?>, Object> t = new LinkedHashMap<>();

        Solution91 solution91 = new Solution91();

        test1(Solution91.class, solution91, t);

        Solution91 solution911 = (Solution91)t.get(Solution91.class);
        System.out.println("test");
    }


    static void test1(Class key, Object value,Map<Class<?>, Object> t) {
        if (value.getClass().equals(key)) {
            t.put(Solution91.class, value);
        }
    }

    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == 0) {
            return 0;
        }

        int pre = 1;
        int pPre = 0;
        for (int i = 1; i < chars.length; i++) {


        }


        return 0;
    }

    @Test
    public void test(){

        Assert.assertEquals(2, numDecodings("12"));

        Assert.assertEquals(3, numDecodings("226"));

        Assert.assertEquals(0, numDecodings("0"));

        Assert.assertEquals(0, numDecodings("06"));

        Assert.assertEquals(30, numDecodings("1216238123"));

        Assert.assertEquals(0, numDecodings("1210623801203"));
    }

}