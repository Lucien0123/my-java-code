package com.lucien.myjavacode;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huoershuai
 * Created on 2020-10-14
 */
public class main9 {

    public static void main(String[] args) {
        String revenue = "3.3500000000000000";
        Double yuan = NumberUtils.toDouble(revenue);
        System.out.println(yuan2Cent(yuan));

        long userId = 2173370816L;
        int aaaa = (int) (userId % 100);
        System.out.println(aaaa);
    }

    /**
     * 人民币元转化为分取整
     */
    private static long yuan2Cent(Double yuan) {
        if (Double.isNaN(yuan)) {
            return 0L;
        }
        return (long) (yuan * 1000 / 10);
    }
}
