package leetcode.others;
//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
// 示例 3：
//
//
//输入：s = "a"
//输出："a"
//
//
// 示例 4：
//
//
//输入：s = "ac"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
//
// Related Topics 字符串 动态规划
// 👍 3433 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution5 {
    public static void main(String[] args) {
//        ListNode n3 = new ListNode(2, null);
//        ListNode n2 = new ListNode(1, n3);
//        ListNode n1 = new ListNode(0, n2);
//
//        System.out.println(rotateRight(n1, 3));
        System.out.println(longestPalindrome("cbbd"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        char[] longChars = new char[s.length() + s.length() + 1];
        Arrays.fill(longChars, 'T');
        for (int i = 1,j=0; i < longChars.length ; i = i + 2,j++) {
            longChars[i] = chars[j];
        }

        int begin = 1;
        int length = 3;

        for (int i = 3 ; i < longChars.length; i= i + 2) {
            int j = 2;
            for (; i + j < longChars.length && i - j >= 0 ; j = j + 2) {
                if (longChars[i+j] != longChars[i-j]){
                    break;
                }
            }
            j = j - 2;
            int tmpLen = j + j + 1;
            if (length < tmpLen) {
                length = tmpLen;
                begin = i - j;
            }
        }


        return   longChars.toString().substring(begin, begin + length ).replaceAll("T","");
    }
}