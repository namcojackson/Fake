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

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;

import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
class MdseStorePkgCache extends LocalDataCacheBase {

    /** TMsg Cache */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** TMsgArray Cache */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    MDSE_STORE_PKGTMsgArray getTMsgArray(FindCondition findCondition) {

        return (MDSE_STORE_PKGTMsgArray) super.getTMsgArray(MDSE_STORE_PKGTMsg.class, findCondition, tMsgArrayCache);
    }

    MDSE_STORE_PKGTMsg getTMsgByKey(String glblCmpyCd, String pkgUomCd, String mdseCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(pkgUomCd).append(",");
        sb.append(mdseCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final MDSE_STORE_PKGTMsg reqTMsg = new MDSE_STORE_PKGTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.pkgUomCd.setValue(pkgUomCd);
            reqTMsg.mdseCd.setValue(mdseCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (MDSE_STORE_PKGTMsg) resTMsg;
    }

}
