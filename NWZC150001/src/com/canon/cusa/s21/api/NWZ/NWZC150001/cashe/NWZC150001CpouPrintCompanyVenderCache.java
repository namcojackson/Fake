/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsg;
import business.db.PRNT_CMPY_VNDTMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * This class is the Cache (VNDTMsgArray).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2012   CUSA            M.Fuji          Create          N/A
 *</pre>
 */
public class NWZC150001CpouPrintCompanyVenderCache extends NWZC150001CpouLocalDataCacheBase {

    /** cache */
    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();

    public PRNT_CMPY_VNDTMsg getTMsgByKey(String glblCmpyCd, String prntCmpyVndCd) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(prntCmpyVndCd);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final PRNT_CMPY_VNDTMsg reqTMsg = new PRNT_CMPY_VNDTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.prntCmpyVndCd.setValue(prntCmpyVndCd);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (PRNT_CMPY_VNDTMsg) resTMsg;
    }

}
