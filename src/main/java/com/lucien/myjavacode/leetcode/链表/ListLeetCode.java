package com.lucien.myjavacode.leetcode.链表;

/**
 * 链表得操作
 * @author huoershuai
 * Created on 2022-01-12
 */
public class ListLeetCode {

    public static void main(String[] args) {
        ListNode tail = new ListNode(5, null);
        ListNode node4 = new ListNode(4, tail);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);

        ListNode b = reverseKGroup(head, 2);


        String aa = "123 12";
        System.out.println(Character.isSpaceChar(aa.charAt(1)));

    }

    /**
     * 通过快慢指针找到环的点
     * 一次循环，找到相交的点
     *   二层循环，同步调找到相交的点
     *
     * 2(l + p) = l + p + k * r
     * l + p = k * r
     * l = (k-1) * r + (r-p)
     * 含义：从head走到st 等于 从meet走到st然后再绕k-1圈
     * */
    public static ListNode deleteCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode solw = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            solw = solw.next;
            fast = fast.next.next;
            if (solw == fast) { // 找到了meet的点
                ListNode ptr = head;
                while (head != solw) {
                    head = head.next;
                    solw = solw.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     * 通过快慢指针判断链表是否有环，如果有环得话，快慢指针必定相遇
     * 例如两个人在操场上跑步，只要有速度查，一定可以找到最小公倍数
     * 快指针：每次走两步
     * 慢指针：head本身每次走一步
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;  // 快指针
            head = head.next;       // 慢指针
            if (fast == head) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个链表，每k个为一组进行翻转，返回反转后的链表
     * 1，2，3，4，5
     * 2，1，4，3，5
     *
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode product = new ListNode(0, head);  // 访问入口
        ListNode last = product;
        while (head != null) {
            // 1、从head往后走k步找到一组，head开头、end结尾
            ListNode end = getEnd(head, k);
            if (end == null) { // 组不够k个元素就不反转
                break;
            }
            ListNode nextGroupHead = end.next;

            // 2、一组内部（head 到 end之间）要反转（调用反转链表）
            reverseList1(head, nextGroupHead);

            // 3、上一组的结束指向这一组的开始（end）
            head.next = nextGroupHead;
            last.next = end;
            // 3.1、更新下一组，更新last，这一组的结束（head）作为上一组的结束last
            last = head;
            head = nextGroupHead;
        }
        return product.next;
    }

    /**
     * 给定一个单链表得head，请反转链表并返回反转后的链表
     * 1，2，4，5，8
     * 8，5，4，2，1
     * 解析：
     *   原始：1，2，4，5，8，null
     *   开始操作：null，1，2，4，5，8，null  head指向1，last指向null【last只是辅助变量，两个null很有帮助理解】
     *   解析：相当于在开头做两个指针，然后两个指针逐步开始向后移动。head最终指向null、last指向8
     */
    public static ListNode reverseList(ListNode head, ListNode stop) {
        ListNode last = stop;
        /* 首先是遍历这个链表 */
        while (head != stop) {
            ListNode nextHead = head.next;
            head.next = last;
            last = head;
            head = nextHead;
        }
        return last;
    }

    /** 反转链表 */
    public static void reverseList1(ListNode head, ListNode stop) {
        ListNode last = stop;
        /* 首先是遍历这个链表 */
        while (head != stop) {
            ListNode nextHead = head.next;
            head.next = last;
            last = head;
            head = nextHead;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 返回走k-1步后的点
     */
    public static ListNode getEnd(ListNode head, int k) {
        for (int i = 1; i < k; i++) {
            head = head.next;
            if (head == null) return null;
        }
        return head;
    }
}
