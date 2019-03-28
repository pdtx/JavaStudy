/**
 * @description: 腾讯笔记50题
 * @author: fancying
 * @create: 2019-03-27 15:09
 **/
package leetcode;

import java.util.Stack;

public class Tencent50 {


    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 思路： l1 插入到 l2 中就行
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node1,node2,head;
        if( l1 == null)
            return l2;

        if(l2 == null)
            return l1;
        head = l1.val <= l2.val ? l1 : l2;
        node1 = l1;
        node2 = null;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                node1 = l1.next;
                if (node2 != null)
                    node2.next = l1;
                l1.next = l2;
                node2 = l1;
                l1  = node1;
            } else {
                node2 = l2;
                l2 = l2.next;
            }
        }

        if (l1 != null)
            node2.next = l1;

        return head;
    }


    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 示例 1:
     *
     * 输入: 121
     * 输出: true
     * */
    public boolean isPalindrome(int x) {
        //return (new StringBuilder(String.valueOf(x)).reverse().toString()).equals(String.valueOf(x));

        if(x < 0)
            return false;
        char s[] = (String.valueOf(x)).toCharArray();
        for(int i = 0, j = s.length-1; i <= j; ) {
            if(s[i++] != s[j--])
                return false;
        }
        return true;
    }


    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     *  思路：求数组的递增片段 最大值减去最小值
     * */
    public int maxProfit(int[] prices) {
        int sum = 0;
        int min = 0;
        int max = 0;
        while (max < prices.length - 1) {
            if (prices[max] < prices[max + 1]) {
                max ++ ;
            } else {
                sum = sum + prices[max] - prices[min];
                min = ++max;
            }
        }
        if (max == prices.length - 1)
            sum = sum + prices[max] - prices[min];
        return sum;
    }


    /**
     *给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意你不能在买入股票前卖出股票。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     *     思路： 找到当前价格的最小值
     * */
    public int maxProfit2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i : prices) {
            if (min > i)
                min = i;
            if(max < i - min)
                max = i - min;
        }
        return max;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     *
     * 思路：
     * 每次只能爬两阶 楼梯  f(n) = f(n-1) + f(n-2); 到最后一步要么一次要么两次
     * 每次最多爬三阶的话  f(n) = f(n-1) + f(n-2) + f(n-3)
     * */
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        int a1 = 1;
        int a2 = 1;
        int sum = 0;
        while (n > 1) {
            sum = a1 + a2;
            a1 = a2;
            a2 = sum;
            n--;
        }
        return sum;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     *     左括号必须用相同类型的右括号闭合。
     *     左括号必须以正确的顺序闭合。
     *
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * */
    public boolean isValid(String s) {
        char ch[] = s.toCharArray();
        if(ch.length % 2 == 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for (char c : ch) {
            if (stack.empty()) {
                stack.push(c);
            } else {
                if(equal(stack.peek(),c))
                    stack.pop();
                else
                    stack.push(c);
            }
        }
        return stack.empty();
    }
    private boolean equal(char a, char b) {
        if(a == '(' && b == ')')
            return true;
        if(a == '[' && b == ']')
            return true;
        if(a == '{' && b == '}')
            return true;
        return false;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * */
    public int reverse(int x) {
        if (x > -1 && x < 10)
            return x;
        boolean isNegative = x < 0 ;
        if (isNegative)
            x = -1 * x;
        while (x % 10 == 0)
            x /= 10;
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        try {
            x = Integer.valueOf((sb.reverse()).toString());
        }catch (Exception e) {
            return 0;
        }
        if (isNegative)
            x *= -1;
        return x;
    }
    public static void main(String[] args) {
        int x = 1534236469;
        Tencent50 tencent = new Tencent50();
        System.out.println(tencent.reverse(x));
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}