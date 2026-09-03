package com.lucien.myjavacode.leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huoershuai
 * @date 2026/09/03
 */
public class LRUCache {
    // 本地缓存, 保存指向实际数据Node的Map
    private final Map<Integer, Node> cache = new HashMap<>();

    // 单链表的头，最新使用的元素放在头
    private final Node head;
    // 单链表的尾，最久未使用的元素放在尾
    private final Node tail;

    // 当前元素数量
    private int size;

    // cache缓存的容量
    private final int cap;

    public LRUCache(int capacity) {
        size = 0;
        cap = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }


    public int get(int key) {
        Node target = cache.get(key);
        if(target == null) {
            return -1;
        }
        moveToHead(target);
        return target.value;
    }

    public void put(int key, int value) {
        Node tmp = cache.get(key);
        if(tmp != null) {
            tmp.value = value;
            moveToHead(tmp);
            return ;
        }
        Node newNode = new Node(key, value);
        addToHead(newNode);
        cache.put(key, newNode);
        size++;
        // 判断是否超出的容量，如果超出了容量，则从尾部剔除一个node
        // 同时删除cache缓存Map
        if (size > cap) {
            Node lastNode = removeTail();
            if(lastNode == null) {
                return ;
            }
            cache.remove(lastNode.key);
            size --;
        }
    }

    private void addToHead(Node newNode) {
        Node firstNode = head.next;
        head.next = newNode;
        firstNode.pre = newNode;
        newNode.pre = head;
        newNode.next = firstNode;
    }

    private void moveToHead(Node curNode) {
        if(size == 1) {
            return ;
        }
        Node curPre = curNode.pre;
        Node curNext = curNode.next;
        curPre.next = curNext;
        curNext.pre = curPre;
        addToHead(curNode);
    }

    private Node removeTail() {
        if (size == 0) {
            return null;
        }
        Node lastNode = tail.pre;
        Node lastNodePre = lastNode.pre;
        tail.pre = lastNodePre;
        lastNodePre.next = tail;
        return lastNode;
    }

    public static class Node {
        private int key;
        private int value;
        private Node pre;
        private Node next;

        public Node() {}

        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }
}
