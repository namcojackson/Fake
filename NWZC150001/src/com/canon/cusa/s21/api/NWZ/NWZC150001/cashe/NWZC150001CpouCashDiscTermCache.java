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
import business.db.CASH_DISC_TERMTMsg;
import business.db.CASH_DISC_TERMTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouCashDiscTermCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public CASH_DISC_TERMTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (CASH_DISC_TERMTMsgArray) super.getTMsgArray(CASH_DISC_TERMTMsg.class, findCondition, tMsgArrayCache);
    }

    public CASH_DISC_TERMTMsg getTMsgByKey(String glblCmpyCd, String cashDiscTermCd, String cashDiscTermEffFromDt, String cashDiscTermEffThruDt, String cashDiscTermDtlNum) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(cashDiscTermCd).append(",");
        sb.append(cashDiscTermEffFromDt).append(",");
        sb.append(cashDiscTermEffThruDt).append(",");
        sb.append(cashDiscTermDtlNum);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final CASH_DISC_TERMTMsg reqTMsg = new CASH_DISC_TERMTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.cashDiscTermCd.setValue(cashDiscTermCd);
            reqTMsg.cashDiscTermEffFromDt.setValue(cashDiscTermEffFromDt);
            reqTMsg.cashDiscTermEffThruDt.setValue(cashDiscTermEffThruDt);
            reqTMsg.cashDiscTermDtlNum.setValue(cashDiscTermDtlNum);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (CASH_DISC_TERMTMsg) resTMsg;
    }

}
