/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC014001.cache;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/12   Fujitsu         T.Yoshida       Create          S21_NA#11618 (For Performance)
 * </pre>
 */
public class LocalDataCacheBase {

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

    protected EZDTMsgArray getTMsgArray(Class< ? > tMsgClass, FindCondition findCondition, S21LRUMap<String, EZDTMsgArray> cacheMap) {

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
