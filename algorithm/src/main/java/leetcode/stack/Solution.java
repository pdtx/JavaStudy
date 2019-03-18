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

    /**
     * 你现在是棒球比赛记录员。
     * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
     * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
     * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
     * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
     * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
     *
     * 每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
     * 你需要返回你在所有回合中得分的总和。
     *
     * 示例 1:
     *
     * 输入: ["5","2","C","D","+"]
     * 输出: 30
     * 解释:
     * 第1轮：你可以得到5分。总和是：5。
     * 第2轮：你可以得到2分。总和是：7。
     * 操作1：第2轮的数据无效。总和是：5。
     * 第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。
     * 第4轮：你可以得到5 + 10 = 15分。总数是：30。
     * */
    public int calPoints(String[] ops) {

        Stack<Integer> score = new Stack<>();
        int cScore;
        for (String str : ops) {
            if("C".equals(str)) {
                score.pop();
                continue;
            }
            if("D".equals(str)) {
                cScore = score.peek() * 2;
                score.push(cScore);
                continue;
            }
            if("+".equals(str)) {
                int last = score.pop();
                cScore = last + score.peek();
                score.push(last);
                score.push(cScore);
                continue;
            }
            cScore = Integer.valueOf(str);
            score.push(cScore);
        }
        cScore = 0;
        while (!score.empty()) {
            cScore += score.pop();
        }

        return cScore;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
       // System.out.println(solution.decodeAtIndex1("a2345678999999999999999" ,1));
        //System.out.println(solution.decodeAtIndex("y75lgfqyn4re8treuyrs9pqxfgvf3rrtqxr6lrm8ymxawwf97jcm5itz8dpvjig3o9iartdajjeoo58ipu6wmuozzpjzzfzrciy6",292404628));
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        System.out.println(solution.calPoints(ops));
    }



}