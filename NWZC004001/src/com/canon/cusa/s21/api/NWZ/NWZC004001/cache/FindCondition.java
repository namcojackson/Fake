/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC004001.cache;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/04   Fujitsu         T.Yoshida       Create          S21_NA#14512
 * </pre>
 */
public class FindCondition {

    /** SQL ID */
    private String sqlId = null;

    /** Linked HashMap */
    private LinkedHashMap<String, Serializable> conditionValueMap = new LinkedHashMap<String, Serializable>();

    /**
     * Constructor
     * @param sqlId SQL ID
     */
    public FindCondition(String sqlId) {
        this.sqlId = sqlId;
    }

    /**
     * Add Condition
     * @param key Key
     * @param value Value (String)
     */
    public void addCondition(String key, String value) {
        conditionValueMap.put(key, value);
    }

    /**
     * Add Condition
     * @param key Key
     * @param value Value (BigDecimal)
     */
    public void addCondition(String key, BigDecimal value) {
        conditionValueMap.put(key, value);
    }

    Map<String, Serializable> getConditionValueMap() {
        return conditionValueMap;
    }

    String getSqlId() {
        return sqlId;
    }
}