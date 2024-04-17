/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import parts.common.EZDTMsgArray;
import business.db.SHIP_FROM_LOC_LIST_VTMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * </pre>
 */
public final class ShipFromLocListViewCache extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = ShipFromLocListViewCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** Private Constructor */
    private ShipFromLocListViewCache() {
    }

    /**
     * Get Singleton Instance
     * @return ShipFromLocListViewCache
     */
    public static ShipFromLocListViewCache getInstance() {

        ShipFromLocListViewCache myInstance = (ShipFromLocListViewCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new ShipFromLocListViewCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get SHIP_FROM_LOC_LIST_VTMsgArray
     * @param findCondition FindCondition
     * @return SHIP_FROM_LOC_LIST_VTMsgArray
     */
    public SHIP_FROM_LOC_LIST_VTMsgArray getTMsgArray(FindCondition findCondition) {
        return (SHIP_FROM_LOC_LIST_VTMsgArray) super.getTMsgArray(SHIP_FROM_LOC_LIST_VTMsg.class, findCondition, tMsgArrayCache);
    }
}
