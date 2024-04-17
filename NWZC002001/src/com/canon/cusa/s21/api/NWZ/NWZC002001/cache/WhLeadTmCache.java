/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import parts.common.EZDTMsgArray;
import business.db.WH_LEAD_TMTMsg;
import business.db.WH_LEAD_TMTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * </pre>
 */
public final class WhLeadTmCache extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = WhLeadTmCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /** Private Constructor */
    private WhLeadTmCache() {
    }

    /**
     * Get Singleton Instance
     * @return WhLeadTmCache
     */
    public static WhLeadTmCache getInstance() {

        WhLeadTmCache myInstance = (WhLeadTmCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new WhLeadTmCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get WH_LEAD_TMTMsgArray
     * @param findCondition FindCondition
     * @return WH_LEAD_TMTMsgArray
     */
    public WH_LEAD_TMTMsgArray getTMsgArray(FindCondition findCondition) {
        return (WH_LEAD_TMTMsgArray) super.getTMsgArray(WH_LEAD_TMTMsg.class, findCondition, tMsgArrayCache);
    }
}
