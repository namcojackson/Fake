/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * OrdLineValSetCache
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/22   Fujitsu         S.Takami        Create          S21_NA#24256
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsgArray;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001OrdLineValSetCache extends NWZC150001CpouLocalDataCacheBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = NWZC150001OrdLineValSetCache.class.getName();


    /** Private Constructor */
    private NWZC150001OrdLineValSetCache() {
    }

    /**
     * Get Singleton Instance
     * @return ConfigTpCache
     */
    public static NWZC150001OrdLineValSetCache getInstance() {

        NWZC150001OrdLineValSetCache myInstance = (NWZC150001OrdLineValSetCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWZC150001OrdLineValSetCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

//    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public ORD_LINE_VAL_SETTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (ORD_LINE_VAL_SETTMsgArray) super.getTMsgArray(ORD_LINE_VAL_SETTMsg.class, findCondition, tMsgArrayCache);
    }

//    public ORD_LINE_VAL_SETTMsg getTMsgByKey(String glblCmpyCd, String sellToCustCd, String billToCustCd) {
//
//        final StringBuilder sb = new StringBuilder();
//        sb.append(glblCmpyCd).append(",");
//        sb.append(sellToCustCd).append(",");
//        sb.append(billToCustCd);
//
//        final String cacheKey = sb.toString();
//
//        EZDTMsg resTMsg = tMsgCache.get(cacheKey);
//
//        if (resTMsg == null) {
//
//            // find by key
//            final ORD_LINE_VAL_SETTMsg reqTMsg = new ORD_LINE_VAL_SETTMsg();
//            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
//            reqTMsg.sellToCustCd.setValue(sellToCustCd);
//            reqTMsg.billToCustCd.setValue(billToCustCd);
//
//            resTMsg = super.findByKey(reqTMsg);
//            if (resTMsg != null) {
//                tMsgCache.put(cacheKey, resTMsg);
//            }
//        }
//
//        return (ORD_LINE_VAL_SETTMsg) resTMsg;
//    }
}
