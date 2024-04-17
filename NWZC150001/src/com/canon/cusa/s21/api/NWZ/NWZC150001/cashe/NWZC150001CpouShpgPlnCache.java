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
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouShpgPlnCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public SHPG_PLNTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (SHPG_PLNTMsgArray) super.getTMsgArray(SHPG_PLNTMsg.class, findCondition, tMsgArrayCache);
    }

    public SHPG_PLNTMsgArray getTMsgArrayForUpdate(NWZC150001CpouFindCondition findCondition) {

        return (SHPG_PLNTMsgArray) super.getTMsgArrayForUpdate(SHPG_PLNTMsg.class, findCondition, tMsgArrayCache);
    }

    public SHPG_PLNTMsg getTMsgByKey(String glblCmpyCd, String shpgPlnNum) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(shpgPlnNum);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final SHPG_PLNTMsg reqTMsg = new SHPG_PLNTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.shpgPlnNum.setValue(shpgPlnNum);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (SHPG_PLNTMsg) resTMsg;
    }

}
