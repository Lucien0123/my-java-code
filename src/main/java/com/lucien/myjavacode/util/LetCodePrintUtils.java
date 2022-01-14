package com.lucien.myjavacode.util;

/**
 * @author huoershuai
 * Created on 2022-01-12
 */
public class LetCodePrintUtils {

    public static String printLetCode(int[] a) {
        if (a == null) {
            return "this is null!!!";
        }
        StringBuilder sb = new StringBuilder();
        if (a.length > 0) {
            for (int i : a) {
                sb.append(i).append(", ");
            }
        }
        return sb.toString();
    }
}
