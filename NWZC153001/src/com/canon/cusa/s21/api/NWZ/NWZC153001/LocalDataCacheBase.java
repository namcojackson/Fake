/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC153001;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
class LocalDataCacheBase {

    protected String createCacheKey(FindCondition findCondition) {

        final StringBuilder sb = new StringBuilder(256);
        sb.append("sqlId=").append(findCondition.getSqlId()).append(",");

        final Iterator<Entry<String, Serializable>> it = findCondition.getConditionValueMap().entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Serializable> entry = it.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
        }

        return sb.toString();
    }

    private EZDTMsg createRequestTMsg(EZDTMsg reqTMsg, FindCondition findCondition) {

        // SQL_ID
        reqTMsg.setSQLID(findCondition.getSqlId());

        // CONDITION_VALUES
        Iterator<Entry<String, Serializable>> it = findCondition.getConditionValueMap().entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Serializable> entry = it.next();
            reqTMsg.setConditionValue(entry.getKey(), entry.getValue());
        }

        return reqTMsg;
    }

    private EZDTMsgArray findByCondition(EZDTMsg reqTMsg) {
        return S21ApiTBLAccessor.findByCondition(reqTMsg);
    }

    private EZDTMsgArray findByConditionForUpdate(EZDTMsg reqTMsg) {
        return S21ApiTBLAccessor.findByConditionForUpdate(reqTMsg);
    }

    protected EZDTMsg findByKey(EZDTMsg reqTMsg) {
        return S21FastTBLAccessor.findByKey(reqTMsg);
    }

    protected EZDTMsgArray getTMsgArray(Class tMsgClass, FindCondition findCondition, S21LRUMap<String, EZDTMsgArray> cacheMap) {

        String cacheKey = createCacheKey(findCondition);

        EZDTMsgArray resTMsgArray = cacheMap.get(cacheKey);
        if (resTMsgArray == null) {
            EZDTMsg reqTMsg = newEZDTMsg(tMsgClass.getName());
            resTMsgArray = findByCondition(createRequestTMsg(reqTMsg, findCondition));
            if (resTMsgArray != null) {
                cacheMap.put(cacheKey, resTMsgArray);
            }
        }

        if (resTMsgArray == null) {
            resTMsgArray = newEZDTMsgArray(tMsgClass.getName() + "Array");
        }
        return resTMsgArray;
    }

    protected EZDTMsgArray getTMsgArrayForUpdate(Class tMsgClass, FindCondition findCondition, S21LRUMap<String, EZDTMsgArray> cacheMap) {

        EZDTMsg reqTMsg = createRequestTMsg(newEZDTMsg(tMsgClass.getName()), findCondition);

        EZDTMsgArray resTMsgArray = findByConditionForUpdate(reqTMsg);
        if (resTMsgArray != null) {
            String cacheKey = createCacheKey(findCondition);
            cacheMap.put(cacheKey, resTMsgArray);
        }

        if (resTMsgArray == null) {
            resTMsgArray = newEZDTMsgArray(tMsgClass.getName() + "Array");
        }
        return resTMsgArray;
    }

    private static EZDTMsg newEZDTMsg(String classNm) {
        try {
            return (EZDTMsg) Class.forName(classNm).newInstance();
        } catch (InstantiationException e) {
            throw new S21AbendException(e);
        } catch (IllegalAccessException e) {
            throw new S21AbendException(e);
        } catch (ClassNotFoundException e) {
            throw new S21AbendException(e);
        }
    }

    private static EZDTMsgArray newEZDTMsgArray(String classNm) {
        try {
            return (EZDTMsgArray) Class.forName(classNm).newInstance();
        } catch (InstantiationException e) {
            throw new S21AbendException(e);
        } catch (IllegalAccessException e) {
            throw new S21AbendException(e);
        } catch (ClassNotFoundException e) {
            throw new S21AbendException(e);
        }
    }

}
