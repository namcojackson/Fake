/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.AVAL_INVTY_APP_IDTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouAvalInvtyAppIdCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public AVAL_INVTY_APP_IDTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (AVAL_INVTY_APP_IDTMsgArray) super.getTMsgArray(AVAL_INVTY_APP_IDTMsg.class, findCondition, tMsgArrayCache);
    }

    public AVAL_INVTY_APP_IDTMsg getTMsgByKey(String glblCmpyCd, String bizAppId, String locStsCd, String stkStsCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(bizAppId).append(",");
        sb.append(locStsCd).append(",");
        sb.append(stkStsCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final AVAL_INVTY_APP_IDTMsg reqTMsg = new AVAL_INVTY_APP_IDTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.bizAppId.setValue(bizAppId);
            reqTMsg.locStsCd.setValue(locStsCd);
            reqTMsg.stkStsCd.setValue(stkStsCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (AVAL_INVTY_APP_IDTMsg) resTMsg;
    }

}
