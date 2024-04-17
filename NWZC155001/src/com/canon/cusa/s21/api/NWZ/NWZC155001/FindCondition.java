/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC155001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class FindCondition {

    /** SQL ID */
    private String sqlId;

    /** Condition Value Map */
    private LinkedHashMap<String, Serializable> conditionValueMap = new LinkedHashMap<String, Serializable>();

    FindCondition(String sqlId) {
        this.sqlId = sqlId;
    }

    void addCondition(String key, BigDecimal value) {
        conditionValueMap.put(key, value);
    }

    void addCondition(String key, String value) {
        conditionValueMap.put(key, value);
    }

    Map<String, Serializable> getConditionValueMap() {
        return conditionValueMap;
    }

    String getSqlId() {
        return sqlId;
    }

}
