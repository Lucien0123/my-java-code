package com.lucien.myjavacode.leetcode.找第K大的数;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author huoershuai
 * @date 2026/09/05
 */
public class TopKCalculate {

    /**
     * 方法一：使用PriorityQueue: 默认是从小到大排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest111(int[] nums, int k) {
        if (nums.length < k || k < 1) {
            throw new RuntimeException("param error!!");
        }
        // 小顶堆
        PriorityQueue<Integer> minHeadHeap = new PriorityQueue<>();
        // 大顶堆
        PriorityQueue<Integer> maxHeadHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : nums) {
            add(num, k, minHeadHeap);
        }
        if (minHeadHeap.isEmpty()) {
            throw new RuntimeException("param error!!");
        }
        return minHeadHeap.peek();
    }
    private void add(int v, int k, PriorityQueue<Integer> heap) {
        if (heap == null) {
            throw new RuntimeException("param error!!");
        }
        if (heap.size() < k) {
            heap.offer(v);
            return;
        }
        if (heap.isEmpty()) {
            return;
        }
        if (heap.peek() < v) {
            heap.poll();
            heap.offer(v);
        }
    }


    /**
     * 方法二: 没有使用priorityQueue，另外定义List，0位保存最小的元素
     * ## 每次插入元素时，都要判断是否需要替换最小元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length < k) {
            throw new RuntimeException("param error");
        }
        List<Integer> heap = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            heap = minHeapAppend(heap, nums[i], k);
        }
        return heap.get(0);
    }

    // 构造小根堆，v是待插入的元素，k是堆的大小上限
    private List<Integer> minHeapAppend(List<Integer> heap, int v, int k) {
        if (heap == null) {
            heap = new ArrayList<>();
        }
        if (heap.size() < k) {
            heap.add(v);
            if (heap.size() == k) {
                heap = swapMinHeap(heap);
            }
        } else {
            if (heap.get(0) < v) {
                heap = heap.subList(1, heap.size());
                heap.add(v);
                heap = swapMinHeap(heap);
            }
        }
        return heap;
    }

    private List<Integer> swapMinHeap(List<Integer> heap) {
        if (heap == null || heap.isEmpty()) {
            return new ArrayList<>();
        }
        Integer min = Collections.min(heap);
        int index = heap.indexOf(min);
        // 将最小的元素放在最前
        List<Integer> newHeap = new ArrayList<>();
        heap.remove(index);
        newHeap.add(min);
        newHeap.addAll(heap);
        return newHeap;
    }
}
