/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC011001.cache;

import parts.common.EZDTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/20   Fujitsu         T.Yoshida       Create          S21_NA#11618 (For Performance)
 * </pre>
 */
public final class DataCacheForTBLAccessor extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = DataCacheForTBLAccessor.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> shipToCustTMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

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
     * Get SHIP_TO_CUSTTMsgArray
     * @param findCondition FindCondition
     * @return SHIP_TO_CUSTTMsgArray
     */
    public SHIP_TO_CUSTTMsgArray getShipToCustTMsgArray(FindCondition findCondition) {
        return (SHIP_TO_CUSTTMsgArray) super.getTMsgArray(SHIP_TO_CUSTTMsg.class, findCondition, shipToCustTMsgArrayCache);
    }
}
