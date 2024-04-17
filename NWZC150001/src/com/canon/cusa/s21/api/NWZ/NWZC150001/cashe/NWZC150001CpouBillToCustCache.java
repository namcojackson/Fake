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

import java.math.BigDecimal;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouBillToCustCache extends NWZC150001CpouLocalDataCacheBase {

    private S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public BILL_TO_CUSTTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (BILL_TO_CUSTTMsgArray) super.getTMsgArray(BILL_TO_CUSTTMsg.class, findCondition, tMsgArrayCache);
    }

    public BILL_TO_CUSTTMsg getTMsgByKey(String glblCmpyCd, BigDecimal billToCustPk) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(billToCustPk);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final BILL_TO_CUSTTMsg reqTMsg = new BILL_TO_CUSTTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.billToCustPk.setValue(billToCustPk);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (BILL_TO_CUSTTMsg) resTMsg;
    }

}
