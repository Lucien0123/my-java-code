package com.lucien.myjavacode.JVM调试;
/**
 * Created by lucien on 2018/4/16.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * God bless me!!!
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Description : Java堆内存溢出
 * @Author : lucien
 * @Date : 2018-04-16 下午2:31
 */
public class JavaHeapOOM {

    static class DemoObj {

    }

    public static void main(String[] args) {
        List<DemoObj> list = new ArrayList<>();

        while (1 == 1){
            list.add(new DemoObj());
        }
    }
}
