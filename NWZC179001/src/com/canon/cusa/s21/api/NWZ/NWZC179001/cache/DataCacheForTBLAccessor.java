/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC179001.cache;

import parts.common.EZDTMsgArray;
import business.db.ORD_LINE_SRCTMsg;
import business.db.ORD_LINE_SRCTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/13   Fujitsu         T.Yoshida       Create          S21_NA#14973 (For Performance)
 * </pre>
 */
public final class DataCacheForTBLAccessor extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = DataCacheForTBLAccessor.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> ordLineSrcTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** Private Constructor */
    private DataCacheForTBLAccessor() {
    }

    /**
     * Get Singleton Instance
     * @return DataCacheForTBLAccessor
     */
    public static DataCacheForTBLAccessor getInstance() {

        DataCacheForTBLAccessor myInstance = (DataCacheForTBLAccessor) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new DataCacheForTBLAccessor();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get ORD_LINE_SRCTMsgArray
     * @param findCondition FindCondition
     * @return ORD_LINE_SRCTMsgArray
     */
    public ORD_LINE_SRCTMsgArray getOrdLineSrcTMsgArray(FindCondition findCondition) {
        return (ORD_LINE_SRCTMsgArray) super.getTMsgArray(ORD_LINE_SRCTMsg.class, findCondition, ordLineSrcTMsgArrayCache);
    }
}
