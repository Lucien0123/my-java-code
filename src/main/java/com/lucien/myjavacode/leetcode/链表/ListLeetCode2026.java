package com.lucien.myjavacode.leetcode.链表;

import lombok.Data;

/**
 *
 * @author huoershuai
 * @date 2026/09/02
 */
public class ListLeetCode2026 {

    public static void main(String[] args) {
        ListNode node10 = new ListNode(10, null);
        ListNode node9 = new ListNode(9, node10);
        ListNode node8 = new ListNode(8, node9);
        ListNode node7 = new ListNode(7, node8);
        ListNode node6 = new ListNode(6, node7);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
//        ListNode b = reverse(head);
//        ListNode b = reverse(head, node4);
        ListNode b = reverseListKGroup(head, 4);
        System.out.println(printLinkList(b));
    }

    private static ListNode reverseListKGroup(ListNode head, int k) {
        ListNode result = null;
        ListNode lastHead = head;
        while (true) {
            // 先找到一组的终点
            ListNode end = getGroupEnd(head, k);
            if (end == null) { // 组不够k个元素就不反转
                lastHead.next = head;
                break;
            }

            // 再找到下一组的起点，避免反转后丢失了
            ListNode nextGroupHead = end.next;

            // 将第一组反转
            ListNode tmp = reverse(head, end);

            // 如果result为空，则说明当前反转的是第一组
            if (result == null) {
                result = tmp;
            } else {
                // 否则，将上一组的最后一个节点指向这一组的起始点
                lastHead.next = tmp;
                // 将这一组的起点作为这一组的终点
                lastHead = head;
            }
            // 下一组
            head = nextGroupHead;
        }
        return result;
    }

    /**
     * 指定区段内的元素反转
     * 1,2,3,4,5,6,7,8,9,10 -> 4,3,2,1,5,6,7,8,9,10
     * @param head
     * @param end
     * @return
     */
    private static ListNode reverse(ListNode head, ListNode end) {
        if (end.next == null) {
            return reverse(head);
        }
        ListNode endLimit = end.next;
        ListNode pre = end.next;
        while (head != endLimit) {
            ListNode after = head.next;
            // 每次只处理当前元素的指针，指向前一个元素
            head.next = pre;
            pre = head;
            head = after;
        }
        return pre;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode after = head.getNext();
            // 铭记：每次只处理当前元素的指针，指向前一个元素
            head.next = pre;
            pre = head;
            head = after;
        }
        return pre;
    }

    private static ListNode getGroupEnd(ListNode head, int k) {
        for (int i = 1; i < k; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        return head;
    }

    private static String printLinkList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        sb.append("null");
        return sb.toString();
    }

    @Data
    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
