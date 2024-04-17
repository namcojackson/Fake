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

import java.util.Iterator;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * This class is super class of ThreadLocal Cache Class.
 * @author K.Tajima
 */
public abstract class NWXThreadLocalCacheBase {

    protected static EZDTMsgArray findByCondition(EZDTMsg reqTMsg) {
        return S21ApiTBLAccessor.findByCondition(reqTMsg);
    }

    protected static EZDTMsg findByKey(EZDTMsg reqTMsg) {
        return S21FastTBLAccessor.findByKey(reqTMsg);
    }

    protected static boolean isDebugMode() {
        return EZDDebugOutput.isDebug();
    }

    protected NWXThreadLocalCacheBase() {
    }

    /**
     * clear caching data.
     */
    public abstract void clear();

    protected void debug(String str) {
        if (isDebugMode()) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Thread ID: [").append(Thread.currentThread().getId()).append("] ").append(str);
            EZDDebugOutput.println(1, sb.toString(), this);
        }
    }

    protected boolean hasCache(S21LRUMap<String, ?> cacheMap, NWXThreadLocalCacheKeyBase cacheKey) {
        boolean hasCache = cacheMap.get(cacheKey.getKey()) != null;
        if (isDebugMode()) {
            debug("********** Cache List");
            debug(toString(cacheMap));
            debug("********** hasCache? = " + hasCache + " : cacheKey = {" + cacheKey.getKey() + "}");
        }
        return hasCache;
    }

    protected boolean hasValidCount(EZDTMsgArray tMsgArray) {
        return tMsgArray != null && tMsgArray.getValidCount() > 0;
    }

    @Override
    public abstract String toString();

    protected String toString(S21LRUMap<String, ?> cacheMap) {

        StringBuilder sb = new StringBuilder(256);
        sb.append("********** ").append(getClass().getSimpleName()).append(".toString()");

        // size
        int size = cacheMap.size();
        sb.append(" : caching size = ").append(size);

        // keys
        sb.append(" : caching keys = ");
        if (size <= 0) {
            sb.append("none.");
        } else {
            int index = 1;
            Iterator it = cacheMap.getAll().iterator();
            while (it.hasNext()) {
                Map.Entry<String, ?> entry = (Map.Entry<String, ?>) it.next();
                sb.append("[" + index++ + "]{").append(entry.getKey()).append("}");
            }
        }

        return sb.toString();
    }

}
