/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC004001;

/**
 * This class is super class of ThreadLocal Cache Key class.
 * @author K.Tajima
 */
public abstract class NWXThreadLocalCacheKeyBase {

    protected static final String SEPARATOR = ",";

    private String key;

    /**
     * get caching key.
     * @return caching key
     */
    public String getKey() {
        return key;
    }

    protected void setKey(String key) {
        this.key = key;
    }
}
