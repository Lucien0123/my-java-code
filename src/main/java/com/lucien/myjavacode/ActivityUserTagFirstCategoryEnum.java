package com.lucien.myjavacode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huoershuai
 * Created on 2020-11-26
 */
public enum ActivityUserTagFirstCategoryEnum {

    COMMON_TAG(1, "COMMON_TAG", "通用"),
    LIVE_ANCHOR_TAG(2, "LIVE_ANCHOR_TAG", "主播"),
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

    public static ActivityUserTagFirstCategoryEnum getByValue(String value) {
        return MAP.get(value);
    }

    ActivityUserTagFirstCategoryEnum(int code, String value, String desc) {
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
