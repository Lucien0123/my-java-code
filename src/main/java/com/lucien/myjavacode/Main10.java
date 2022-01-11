package com.lucien.myjavacode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * @author huoershuai
 * Created on 2020-11-30
 */
public class Main10 {

    /**
     * 获取指定类型的用户标签
     */
    public static List<FirstUserTagCategory> getAllUserTagCategories(long userId,
            List<Integer> firstCategoryCode) {
        List<FirstUserTagCategory> result = new ArrayList<>();
        List<ActivityUserTagEnum> userTags = Arrays.stream(ActivityUserTagEnum.values()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userTags)) {
            return result;
        }
        ListMultimap<String, SecondUserTagCategory> firstCategoryMap = ArrayListMultimap.create();
        userTags.forEach(e -> {
            String desc = e.getSecondCategory().getDesc();
            String value = e.getSecondCategory().getValue();
            int code = e.getCode();
            SecondUserTagCategory secondCategory = new SecondUserTagCategory(code, value, desc);
            firstCategoryMap.put(e.getFirstCategory().getValue(), secondCategory);
        });
        if (firstCategoryMap.size() <= 0) {
            return result;
        }
        firstCategoryMap.keySet().forEach(k -> {
            ActivityUserTagFirstCategoryEnum firstCategoryEnum =
                    ActivityUserTagFirstCategoryEnum.getByValue(k);
            if (firstCategoryEnum != null) {
                if (CollectionUtils.isNotEmpty(firstCategoryCode) && !firstCategoryCode.contains(firstCategoryEnum.getCode())) {
                } else {
                    FirstUserTagCategory firstCategory = new FirstUserTagCategory();
                    firstCategory.setValue(firstCategoryEnum.getValue());
                    firstCategory.setDesc(firstCategoryEnum.getDesc());
                    firstCategory.setSecondCategories(firstCategoryMap.get(k));
                    result.add(firstCategory);
                }
            }
        });
        return result;
    }

    public static void main(String[] args) {
        getAllUserTagCategories(0, Collections.emptyList());
    }

    public static class FirstUserTagCategory {
        private String value;
        private String desc;
        private List<SecondUserTagCategory> secondCategories;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<SecondUserTagCategory> getSecondCategories() {
            return secondCategories;
        }

        public void setSecondCategories(List<SecondUserTagCategory> secondCategories) {
            this.secondCategories = secondCategories;
        }
    }

    static class SecondUserTagCategory {
        private int code;
        private String value;
        private String desc;

        public SecondUserTagCategory(int code, String value, String desc) {
            this.code = code;
            this.value = value;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
