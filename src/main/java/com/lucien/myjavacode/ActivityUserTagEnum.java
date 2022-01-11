package com.lucien.myjavacode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户标签一级类目标签枚举
 * @author huoershuai
 * Created on 2020-11-26
 */
public enum ActivityUserTagEnum {

    LIVE_ANCHOR_FAN_CATEGORY(
            1,
            ActivityUserTagFirstCategoryEnum.LIVE_ANCHOR_TAG,
            ActivityUserTagSecondCategoryEnum.FAN_CATEGORY_CODE),
    LIVE_ANCHOR_LIFE_CYCLE(
            2,
            ActivityUserTagFirstCategoryEnum.LIVE_ANCHOR_TAG,
            ActivityUserTagSecondCategoryEnum.LIFE_CYCLE_CODE),
    ;

    private static final Map<Integer, ActivityUserTagEnum> MAP = new HashMap<>();

    static {
        Arrays.stream(ActivityUserTagEnum.values()).forEach(e -> {
            MAP.put(e.getCode(), e);
        });
    }

    public static ActivityUserTagEnum getByCode(Integer code) {
        return MAP.get(code);
    }

    private int code;
    private ActivityUserTagFirstCategoryEnum firstCategory;
    private ActivityUserTagSecondCategoryEnum secondCategory;

    ActivityUserTagEnum(int code, ActivityUserTagFirstCategoryEnum firstCategory,
            ActivityUserTagSecondCategoryEnum secondCategory) {
        this.code = code;
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ActivityUserTagFirstCategoryEnum getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(ActivityUserTagFirstCategoryEnum firstCategory) {
        this.firstCategory = firstCategory;
    }

    public ActivityUserTagSecondCategoryEnum getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(ActivityUserTagSecondCategoryEnum secondCategory) {
        this.secondCategory = secondCategory;
    }
}
