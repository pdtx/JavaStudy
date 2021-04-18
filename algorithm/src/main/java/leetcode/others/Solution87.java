package leetcode.others;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution87 {

    public static void main(String[] args) {

        String s1 = "great", s2 = "rgeat";
        System.out.println(isScramble(s1, s2));

        System.out.println(isScramble("abcde", "caebd"));
        System.out.println(isScramble("a", "a"));


        s1 = "greatgreat";
        s2 = "rgeatgreat";

        // true
        System.out.println(isScramble(s1,s2));


        s1 = "abcdbdacbdac";
        s2 = "bdacabcdbdac";
        // true
        System.out.println(isScramble(s1,s2));
    }

    /**
    *  重新查看该题
    */
    public static boolean isScramble(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.length() <= 2) {
            return s1.equals(s2) || (s1.length() == 2 && s1.charAt(0) == s2.charAt(1) &&  s1.charAt(1) == s2.charAt(0));
        }

        // 元素不一致也返回false
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }

        int beginIndex = 0;
        boolean r = false;
        do {
            // 检查字符串的第一个元素是否符合标准
            int firstIndex = s1.indexOf(s2.charAt(0), beginIndex);

            if (firstIndex == -1) {
                return false;
            }

            int frontMaxIndex = -1;
            int frontMinIndex = s1.length();

            int[] beginCharIndex = new int[26];
            Arrays.fill(beginCharIndex, 0);
            for (int i = 0; i < firstIndex; i++) {
                int charIndex = s1.charAt(i) - 'a';
                // 在s2中 查找 s1 中前半段的位置
                int curIndex = s2.indexOf(s1.charAt(i), beginCharIndex[charIndex]);
                beginCharIndex[charIndex] = curIndex + 1;
                if (curIndex > frontMaxIndex) {
                    frontMaxIndex = curIndex;
                }
                if (curIndex < frontMinIndex ) {
                    frontMinIndex = curIndex;
                }

            }

            int backMinIndex = s1.length();
            int backMaxIndex = -1;
            for (int i = firstIndex + 1; i > firstIndex && i < s1.length(); i++) {
                int charIndex = s1.charAt(i) - 'a';
                int curIndex = s2.indexOf(s1.charAt(i), beginCharIndex[charIndex]);
                beginCharIndex[charIndex] = curIndex + 1;

                if (curIndex > backMaxIndex) {
                    backMaxIndex = curIndex;
                }
                if (curIndex < backMinIndex ) {
                    backMinIndex = curIndex;
                }

            }


            if (frontMaxIndex < backMinIndex || frontMinIndex > backMaxIndex) {
                // 循环子节点
                String leftS1 = s1.substring(0, firstIndex);
                String leftS2 = s2.substring(1, firstIndex + 1);

                String rightS1 = s1.substring(firstIndex + 1);
                String rightS2 = s2.substring(firstIndex + 1);
                // 1 第一个在前面的串里
                r = (isScramble(leftS1,leftS2) && isScramble(rightS1, rightS2)) ||
                        // 2 第一个在后面的串里
                        (isScramble(leftS1,rightS2) && isScramble(leftS2, rightS1));
            }
            if (r) {
                return true;
            }

            beginIndex++;
        }while (beginIndex < s1.length());

        return false;
    }

    @Test
    public void test1(){

        String s1 = "greatgreat";
        String s2 = "rgeatgreat";

        // true
        Assertions.assertTrue(isScramble(s1, s2));

    }

}