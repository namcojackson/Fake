/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class NWZC150001CpouFindCondition {

    private String sqlId;

    private LinkedHashMap<String, Serializable> conditionValueMap = new LinkedHashMap<String, Serializable>();
    
    public NWZC150001CpouFindCondition(String sqlId) {
        this.sqlId = sqlId;
    }
    
    public void addCondition( String key, BigDecimal value ) {
        conditionValueMap.put( key, value );
    }
    
    public void addCondition( String key, String value ) {
        conditionValueMap.put( key, value );
    }

    public Map<String, Serializable> getConditionValueMap() {
        return conditionValueMap;
    }

    public String getSqlId() {
        return sqlId;
    }

}
