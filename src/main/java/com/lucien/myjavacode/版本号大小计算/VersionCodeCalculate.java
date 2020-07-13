package com.lucien.myjavacode.版本号大小计算;


import org.apache.commons.lang3.StringUtils;

public class VersionCodeCalculate {

    public static int calculate(String v1, String v2) {
        if (!v1.equals(v2)) {
            String[] v1s = v1.split("\\.");
            String[] v2s = v2.split("\\.");

            int lim = Math.min(v1s.length, v2s.length);
            if (v1s.length != v2s.length) {
                return v1s.length - v2s.length;
            }
            if (!v1.equals(v2)) {
                for (int i = 0; i < lim; i++) {
                    if (!v1s[i].equals(v2s[i])) {
                        if (v1s[i].length() == v2s[i].length()) {
                            return v1s[i].compareTo(v2s[i]);
                        }
                        return v1s[i].length() - v2s[i].length();
                    }
                }
            }
        }
        return 0;
    }

    /* 通过英文.分割的N级版本号 String */

    public static boolean versionCompare(String version, String start, String end) {

        if (StringUtils.isBlank(start) && StringUtils.isBlank(end)) {
            return true;
        }
        if ((StringUtils.isNotBlank(start) || StringUtils.isNotBlank(end)) && StringUtils.isBlank(version)) {
            return false;
        }
        if (StringUtils.isNotBlank(version)) {

            if (StringUtils.isNotBlank(start)) {
                if (calculate(version, start) < 0) {
                    return false;
                }
            }
            if (StringUtils.isNotBlank(end)) {
                if (calculate(version, end) > 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String verion = "8.9.11";
        String start = "8.2.0";
        String end = "8.9.18";

        System.out.println(versionCompare(verion, start, end));
    }
}
