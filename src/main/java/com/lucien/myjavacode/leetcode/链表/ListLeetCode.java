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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        // 遍历两个链表，每位相加
        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;
            if (head == null) {
                head = new ListNode(sum % 10);
                tail = head;
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return tail;
    }

    /**
     * 使用 Floyd 判环算法（龟兔赛跑算法）检测链表中环的入口节点。
     *
     * 算法分为两个阶段：
     *
     * 阶段一：判断是否有环，并找到快慢指针的相遇点。
     *   - slow 指针每次走 1 步，fast 指针每次走 2 步。
     *   - 如果链表无环，fast 会先到达末尾（fast == null 或 fast.next == null）。
     *   - 如果链表有环，fast 一定会追上 slow，两指针在环内某点相遇。
     *     原因：进入环后，fast 相对 slow 的速度是 1 步/轮，迟早会套圈相遇。
     *
     * 阶段二：根据相遇点定位环的入口节点。
     *   设：
     *     l  = 从 head 到环入口 start 的步数
     *     p  = 从环入口 start 到相遇点 meet 的步数
     *     r  = 环的长度（周长）
     *
     *   当 slow 与 fast 第一次相遇时：
     *     slow 走过的路程：l + p
     *     fast 走过的路程：2(l + p)   （速度是 slow 的 2 倍）
     *
     *   fast 比 slow 多走了整数圈：
     *     2(l + p) - (l + p) = k * r   （k 为某个正整数，表示 fast 多绕的圈数）
     *     => l + p = k * r
     *     => l = k * r - p
     *     => l = (k - 1) * r + (r - p)
     *
     *   公式含义：
     *     从 head 出发走 l 步到达环入口，
     *     等价于从相遇点 meet 出发，绕环 (k-1) 圈后再走 (r-p) 步也到达环入口。
     *
     *   因此：
     *     让 ptr 从 head 出发，slow 从 meet 出发，两个指针每次都只走 1 步。
     *     当 ptr 走了 l 步到达环入口时，slow 也刚好走了 l 步，
     *     即 slow 绕了 (k-1) 圈并走了 (r-p) 步，同样回到环入口。
     *     两指针相遇的位置就是环的入口节点。
     *
     * 时间复杂度：O(n)，空间复杂度：O(1)
     *
     * @param head 链表头节点
     * @return 环的入口节点；如果链表无环，返回 null
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        // 阶段一：快慢指针找相遇点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 阶段二：ptr 从 head 出发，slow 从相遇点出发，同步前进找环入口
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
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
