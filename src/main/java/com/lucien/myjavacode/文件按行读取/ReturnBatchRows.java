package com.lucien.myjavacode.文件按行读取;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huoershuai
 * Created on 2020-08-20
 */
public class ReturnBatchRows {

    private static final int BATCH_SIZE = 1000;

    public static void main(String[] args) {
        String filePath = "/Users/huoershuai/batch_fix_live_duration-21.txt";
        String filePath2 = "/Users/huoershuai/111.txt";
        String shCode = "java -XX:+AggressiveOpts -XX:+UseStringDeduplication -Xss256k -Xms4G " +
                "-Xmx4G -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+ExitOnOutOfMemoryError -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -Djava.library.path=/usr/local/lib -cp ./*: com.kuaishou.operation.cp.runner.livestream.consumer.CpUserLiveStreamDurationConsumer ";
        String date = " 2021 1 20";
        List<String> batchExecuteUserIdString = batchSplit(filePath2);
        for (String ids : batchExecuteUserIdString) {
            System.out.println(shCode + ids + date);
            System.out.println("");
        }


    }


    private static List<String> batchSplit(String filePath) {
        List<String> result = new ArrayList<>();
        File rawFile = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(rawFile.toPath());
            String line;
            List<String> batchRow = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    batchRow.add(line);
                    if (batchRow.size() >= BATCH_SIZE) {
                        result.add(StringUtils.join(batchRow.toArray(), ","));
                        batchRow.clear();
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(batchRow)) {
                result.add(StringUtils.join(batchRow, ","));
            }
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
}
