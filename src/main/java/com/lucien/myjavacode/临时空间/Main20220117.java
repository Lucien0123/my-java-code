package com.lucien.myjavacode.临时空间;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huoershuai
 * Created on 2022-01-17
 */
public class Main20220117 {

    public static void main(String[] args) {
        System.out.println(Math.pow(100, 2));
        List<String> a = new ArrayList<String>(){{
            add("a");
            add("aaaa");
            add("aa");
        }};

        System.out.println(String.join(", ",
                a.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList())));
    }
}
