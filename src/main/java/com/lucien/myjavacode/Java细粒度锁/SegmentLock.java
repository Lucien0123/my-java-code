package com.lucien.myjavacode.Java细粒度锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lucien on 2017/11/29.
 * 分段锁：先生成一定数量的锁，具体使用的时候再根据key来返回对应的lock。
 * 这是几个实现里最简单，性能最高，也是最终被采用的锁策略。
 * <p>
 * 分段锁，系统提供一定数量的原始锁，根据传入对象的哈希值获取对应的锁并加锁
 * 注意：要锁的对象的哈希值如果发生改变，有可能导致锁无法成功释放!!!
 */
public class SegmentLock<T> {

    private Integer segment = 16;   //设置默认的分段数量

    private final Map<Integer, ReentrantLock> lockMap = new HashMap<>();

    public SegmentLock() {
        init(null, false);
    }

    public SegmentLock(Integer count, Boolean fair) {
        init(count, fair);
    }

    public void init(Integer counts, boolean fair) {
        if (counts != null)
            segment = counts;
        else
            for (int i = 0; i < segment; i++)
                lockMap.put(segment, new ReentrantLock(fair));
    }

    public void lock(T key){
        ReentrantLock lock = lockMap.get(key.hashCode() % segment);
        lock.lock();
    }

    public void unlock(T key){
        ReentrantLock lock = lockMap.get(key.hashCode() % segment);
        lock.unlock();
    }

}
