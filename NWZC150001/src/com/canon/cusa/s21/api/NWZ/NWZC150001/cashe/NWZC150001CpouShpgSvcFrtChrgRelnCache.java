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
import business.db.SHPG_SVC_FRT_CHRG_RELNTMsg;
import business.db.SHPG_SVC_FRT_CHRG_RELNTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouShpgSvcFrtChrgRelnCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public SHPG_SVC_FRT_CHRG_RELNTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (SHPG_SVC_FRT_CHRG_RELNTMsgArray) super.getTMsgArray(SHPG_SVC_FRT_CHRG_RELNTMsg.class, findCondition, tMsgArrayCache);
    }

    public SHPG_SVC_FRT_CHRG_RELNTMsg getTMsgByKey(String glblCmpyCd, String shpgSvcLvlCd, String frtChrgToCd, String frtChrgMethCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(shpgSvcLvlCd).append(",");
        sb.append(frtChrgToCd).append(",");
        sb.append(frtChrgMethCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final SHPG_SVC_FRT_CHRG_RELNTMsg reqTMsg = new SHPG_SVC_FRT_CHRG_RELNTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.shpgSvcLvlCd.setValue(shpgSvcLvlCd);
            reqTMsg.frtChrgToCd.setValue(frtChrgToCd);
            reqTMsg.frtChrgMethCd.setValue(frtChrgMethCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (SHPG_SVC_FRT_CHRG_RELNTMsg) resTMsg;
    }

}
