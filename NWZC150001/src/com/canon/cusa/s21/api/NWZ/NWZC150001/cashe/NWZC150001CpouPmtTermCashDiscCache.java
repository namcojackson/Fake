/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2010   Fujitsu         S.Yamamoto      Create          6758
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PMT_TERM_CASH_DISCTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouPmtTermCashDiscCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public PMT_TERM_CASH_DISCTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (PMT_TERM_CASH_DISCTMsgArray) super.getTMsgArray(PMT_TERM_CASH_DISCTMsg.class, findCondition, tMsgArrayCache);
    }

    public PMT_TERM_CASH_DISCTMsg getTMsgByKey(String glblCmpyCd, String pmtTremCashDiscCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(pmtTremCashDiscCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final PMT_TERM_CASH_DISCTMsg reqTMsg = new PMT_TERM_CASH_DISCTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.pmtTermCashDiscCd.setValue(pmtTremCashDiscCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (PMT_TERM_CASH_DISCTMsg) resTMsg;
    }

}
