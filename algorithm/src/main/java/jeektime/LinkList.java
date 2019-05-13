/**
 * @description: LinkList
 * @author: fancying
 * @create: 2019-04-30 22:31
 **/
package jeektime;

public class LinkList {
    /**
     * 单链表反转
     * */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode rHead = head;
        ListNode next =  head.next;
        head = next;
        rHead.next = null;
        while (next != null) {
            head = head.next;
            next.next = rHead;
            rHead = next;
            next = head;
        }
        return rHead;
    }

    /**
     * 给定一个链表，判断链表中是否有环
     * */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (true) {
            slow = slow.next;
            quick = quick.next;
            if (quick == null)
                return false;
            quick = quick.next;
            if (slow == quick)
                return true;
        }
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        ListNode pre = null;
        temp = head;
        length -= n ;
        while (length-- > 0) {
            pre = temp;
            temp = temp.next;
        }
        if (temp == head)
            return temp.next;
        pre.next = temp.next;
        return head;
    }

    /**
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     * */
    public ListNode middleNode(ListNode head) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        length /= 2 ;
        temp = head;
        while (length-- > 0) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 合并两个有序链表
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(Integer.MIN_VALUE);
        ListNode next = l1;
        pre.next = l1;
        ListNode temp;
        while (l2 != null) {
            while (next != null) {
                if (pre.val <= l2.val && next.val >= l2.val)
                        break;
                else {
                        pre = next;
                        next = next.next;
                }
            }
            temp = l2.next;
            l2.next = next;
            pre.next = l2;
            pre = l2;
            l2 = temp;
        }
        return l1;
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 建堆合并
     * */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        for (int i = 0; i < lists.length; i++)
            head = mergeTwoLists(head, lists[i]);
        return head.next;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
