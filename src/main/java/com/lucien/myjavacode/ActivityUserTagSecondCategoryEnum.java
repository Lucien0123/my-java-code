package com.lucien.myjavacode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户标签二级类目标签枚举
 * @author huoershuai
 * Created on 2020-11-26
 */
public enum ActivityUserTagSecondCategoryEnum {
    FAN_CATEGORY_CODE(11, "fan_category_code", "主播粉丝段"),
    LIFE_CYCLE_CODE(22, "life_cycle_code", "主播生命周期"),
    ;

    private int code;
    private String value;
    private String desc;

    private static final Map<String, ActivityUserTagFirstCategoryEnum> MAP = new HashMap<>();

    static {
        Arrays.stream(ActivityUserTagFirstCategoryEnum.values()).forEach(e -> {
            MAP.put(e.getValue(), e);
        });
    }

    public static ActivityUserTagFirstCategoryEnum getByCode(String value) {
        return MAP.get(value);
    }

    ActivityUserTagSecondCategoryEnum(int code, String value, String desc) {
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
