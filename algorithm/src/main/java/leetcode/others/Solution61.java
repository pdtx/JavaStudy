package leetcode.others;
//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 109 
// 
// Related Topics 链表 双指针 
// 👍 530 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import leetcode.others.ListNode;

import java.util.List;

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
public class Solution61 {
    public static void main(String[] args) {
        ListNode n3 = new ListNode(2, null);
        ListNode n2 = new ListNode(1, n3);
        ListNode n1 = new ListNode(0, n2);

        System.out.println(rotateRight(n1, 3));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode it = head;
        ListNode last = head;
        int length = 0;
        while (it != null) {
            length++;
            last = it;
            it = it.next;
        }

        k = k % length;
        if (k == 0) {
            return head;
        }

        it = head;
        last.next = head;

        for (int i = 1; i < length - k; i++) {
            it = it.next;
        }
        head = it.next;
        it.next = null;
        return head;
    }
}