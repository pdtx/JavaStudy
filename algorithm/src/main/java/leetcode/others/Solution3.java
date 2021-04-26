package leetcode.others;


import java.util.HashSet;
import java.util.Set;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution3 {

    public static void main(String[] args) {
        //String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring("abbcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> characters = new HashSet<>(500);
        int n = s.length();
        int left = -1;
        int ans = 0;
        for (int i = 0; i < n ;i++) {
            if (i != 0) {
                characters.remove(s.charAt(i - 1));
            }

            while (left + 1 < n && !characters.contains(s.charAt(left + 1))) {
                characters.add(s.charAt(left + 1));
                ++left;
            }

            ans = Math.max(ans, left - i + 1);

        }

        return ans;
    }
}