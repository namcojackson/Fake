/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001.cache;

import parts.common.EZDTMsg;
import business.db.SHPG_SVC_LEAD_TMTMsg;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Fujitsu         T.Yoshida       Create          S21_NA#8166 (For Performance)
 * </pre>
 */
public final class ShpgSvcLeadTmCache extends LocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = ShpgSvcLeadTmCache.class.getName();

    /** S21LRUMap */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** Private Constructor */
    private ShpgSvcLeadTmCache() {
    }

    /**
     * Get Singleton Instance
     * @return ShpgSvcLeadTmCache
     */
    public static ShpgSvcLeadTmCache getInstance() {

        ShpgSvcLeadTmCache myInstance = (ShpgSvcLeadTmCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new ShpgSvcLeadTmCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * Get SHPG_SVC_LEAD_TMTMsg
     * @param glblCmpyCd Global Company Code
     * @param shpgSvcLvlCd Shipping Service Level Code
     * @return SHPG_SVC_LEAD_TMTMsg
     */
    public SHPG_SVC_LEAD_TMTMsg getTMsgByKey(String glblCmpyCd, String shpgSvcLvlCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(shpgSvcLvlCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final SHPG_SVC_LEAD_TMTMsg reqTMsg = new SHPG_SVC_LEAD_TMTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.shpgSvcLvlCd.setValue(shpgSvcLvlCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (SHPG_SVC_LEAD_TMTMsg) resTMsg;
    }
}
