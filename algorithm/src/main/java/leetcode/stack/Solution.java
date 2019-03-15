/**
 * @description:
 * @author: fancying
 * @create: 2019-03-15 15:18
 **/
package leetcode.stack;

import java.util.Stack;

public class Solution {

    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * 示例 1：
     *
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”
     *
     * 思路：转换成字符数组 然后找到 # 替换字符 最后删除
     * */
    public boolean backspaceCompare(String S, String T) {
        return sim(S).equals(sim(T));
    }

    private String simplify(String S) {
        // 注意 toCharArray
        char [] s = S.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '#') {
                for (int j = i - 1; j > -1; j--) {
                    if (s[j] != '#') {
                        // error: s[j] = '';
                        s[j] = '#';
                        j = -1;
                    }
                }
            }
        }
        StringBuilder ans  = new StringBuilder();
        for (char a : s) {
            if ( a != '#') {
                ans.append(a);
            }
        }
        return ans.toString();
    }

    private String sim(String S) {
        Stack stack = new Stack();
        StringBuilder sr = new StringBuilder();
        char s[] = S.toCharArray();
        for (char ch: s) {
            if (ch != '#') {
                stack.push(ch);
            }else {
                if (!stack.empty())
                    stack.pop();
            }
        }
        while (!stack.empty()) {
            sr.append(stack.pop());
        }
        return sr.toString();
    }

    /**
     * 给定一个编码字符串 S。为了找出解码字符串并将其写入磁带，从编码字符串中每次读取一个字符，并采取以下步骤：
     *
     *     如果所读的字符是字母，则将该字母写在磁带上。
     *     如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
     *
     * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第 K 个字母。
     *
     * 示例 1：
     *
     * 输入：S = "leet2code3", K = 10
     * 输出："o"
     * 解释：
     * 解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
     * 字符串中的第 10 个字母是 "o"。
     *
     * 错误 ： 最大长度会越界；k 的 边界值需要考虑清楚
     * */
    public String decodeAtIndexError(String S, int K) {
        char s[] = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 ;
        for (char ch: s) {
            if (ch > '1' && ch <= '9') {
                // 必须要 new 不new 的话 sb1始终等于sb
                sb1 = new StringBuilder(sb);
                for (int i = 1; i < ch - '0';i++) {
                    sb.append(sb1);
                }
            }else {
                sb.append(ch);
            }
            if (sb.length() >=  K){
                return String.valueOf(sb.charAt(K-1));
            }
        }
        return null;
    }

    public String decodeAtIndex(String S, int K) {
        long k = K;
        char s[] = S.toCharArray();
        long totalLength = 0;
        Stack<String> code = new Stack<>();
        Stack<Object> codeTimes = new Stack<>();
        for (int i = 0; i < s.length;) {
            StringBuilder sb = new StringBuilder();
            int times = 1;
            while (i < s.length && (s[i] < '2' || s[i] > '9') ){
                sb.append(s[i]);
                i++;
            }
            code.push(sb.toString());
            while ( i < s.length && s[i] > '1' && s[i] <= '9' ) {
                times *= s[i] - '0';
                i++;
            }
            codeTimes.push(times);
            totalLength = (totalLength + sb.length()) * times;
        }

        while (!code.empty()) {
            String cCode = code.pop();
            int cTimes = (int)codeTimes.pop();
            totalLength /= cTimes;
            k = k > totalLength ? k%totalLength : k;
            if (k == 0)
                return String.valueOf(cCode.charAt(cCode.length() - 1));
            if ( k <= totalLength &&  k > totalLength - cCode.length()) {
                int index =(int)(k + cCode.length() - totalLength - 1) ;
                return String.valueOf(cCode.charAt(index));
            } else {
                totalLength -= cCode.length();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
       // System.out.println(solution.decodeAtIndex1("a2345678999999999999999" ,1));
        System.out.println(solution.decodeAtIndex("y75lgfqyn4re8treuyrs9pqxfgvf3rrtqxr6lrm8ymxawwf97jcm5itz8dpvjig3o9iartdajjeoo58ipu6wmuozzpjzzfzrciy6"
                ,292404628));
    }



}