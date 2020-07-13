package com.lucien.myjavacode.文件按行读取;

import com.sun.management.OperatingSystemMXBean;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author huoershuai
 * @Date 2020/6/5
 */
public class FileReader {

    public static void main(String[] args) {

        String filePath = "/Users/huoershuai/demo.txt";
        printJavaProcessRAM();
        System.out.println(statisticsViewCountScanner(filePath));

    }

    /**
     *
     * @param filePath
     * @return
     */
    private static Map<Long, Integer> statisticsViewCount(String filePath) {
        Map<Long, Integer> result = new HashMap<>();
        File rawFile = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(rawFile.toPath());
            String line;
            String[] r;
            Integer count;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    r = line.split(" ");
                    if (r.length >= 4) {
                        Long userId = Long.valueOf(r[2]);
                        count = result.remove(userId);
                        if (count != null && count >= 0) {
                            result.put(userId, count + 1);
                        } else {
                            result.put(userId, 1);
                        }
                    }
                }
            }
            System.out.println("--------------");
            printJavaProcessRAM();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return result;
    }

    /**
     *
     * @param filePath
     * @return
     */
    private static Map<Long, Integer> statisticsViewCountScanner(String filePath) {
        Map<Long, Integer> result = new HashMap<>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream);
            String line;
            String[] r;
            Integer count;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (StringUtils.isNotBlank(line)) {
                    r = line.split(" ");
                    if (r.length >= 4) {
                        Long userId = Long.valueOf(r[2]);
                        count = result.remove(userId);
                        if (count != null && count >= 0) {
                            result.put(userId, count + 1);
                        } else {
                            result.put(userId, 1);
                        }
                    }
                }
            }
            System.out.println("--------------");
            printJavaProcessRAM();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
        return result;
    }

    

    private static void printOperatingSystemRAM() {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
    }

    private static void printJavaProcessRAM() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = mem.getHeapMemoryUsage();
        System.out.println("初始的总内存: " + memoryUsage.getInit() / 1024 / 1024 + "MB");
        System.out.println("最大可用内存: " + memoryUsage.getMax() / 1024 / 1024 + "MB");
        System.out.println("已使用的内存: " + memoryUsage.getUsed() / 1024 / 1024 + "MB");
    }
}
