/**
 * @description:
 * @author: fancying
 * @create: 2019-03-19 11:33
 **/
package leetcode;

import java.util.*;
public class SortSolution {

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 示例 1:
     *
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     * */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            if (set1.contains(i)) {
                ans.add(i);
            }
        }
        int i = 0;
        int a[] = new int[ans.size()];
        for (Integer val : ans) {
            a[i++] = val;
        }
        return a;
    }

    /**
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
     *
     * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）至多有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
     *
     *
     *
     * 示例:
     *
     * 输入: citations = [3,0,6,1,5]
     * 输出: 3
     * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
     *
     *      note : 注意边界条件 times >= i
     * */
    public int hIndex(int[] citations) {
        int times = 0;
        for (int i = citations.length; i > 0 ; i--) {
            times = 0;
            for (int j = 0 ; j < citations.length; j++) {
                if (citations[j] >= i) {
                    times++;
                }
            }
            if (times >= i) {
                times = i;
                i = 0;
            }
        }
        return times;
    }

    /**
     * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     *
     * 示例 1:
     *
     * 输入: [10,2]
     * 输出: 210
     *
     * 注意： 取数字的各位 转换成string 取更方便  多考虑特殊情况 比如说 121 12  ; 0 0 ;
     * */
    public String largestNumber(int[] nums) {
        String sb = "";
        Queue<Integer> queue = new PriorityQueue<>((Integer o1, Integer o2) -> compare(o2,o1));
        for(int i : nums) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            sb += String.valueOf(queue.poll());
        }
        if (sb.toCharArray()[0] == '0')
            return "0";
        return sb;
    }

    private int compare(Integer a, Integer b) {
        if( a.equals(b))
            return 0;
        String strA = String.valueOf(a) +  String.valueOf(a);
        String strB = String.valueOf(b) +  String.valueOf(b);
        if (strA.length() > strB.length()){
            while (strA.length() > strB.length()) {
                strB += strB;
            }
        } else if (strA.length() < strB.length()){
            while (strA.length() < strB.length()) {
                strA += strA;
            }
        }
        for (int i = 0, j = 0; i < strA.length() && j < strB.length(); i++ , j++) {
            if (strA.toCharArray()[i] > strB.toCharArray()[j]) {
                return 1;
            }
            if (strA.toCharArray()[i] < strB.toCharArray()[j]) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SortSolution solution = new SortSolution();
        int[] nums = {0,1,0};
        System.out.println(solution.largestNumber(nums));
    }
}