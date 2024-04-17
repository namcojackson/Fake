/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2009   CUSA            K.Tajima        Create          N/A
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151 (as singleton)
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;

import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouCpoDtlCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsg>      tMsgCache      = new S21LRUMap<String, EZDTMsg>();
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    /**
     * my instance key in TreadLocal.
     */
    private static final String INSTANCE_KEY = NWZC150001CpouCpoDtlCache.class.getName();

    /**
     * singleton instance getter.
     * @return NWXMdseTMsgThreadLocalCache
     */
    public static NWZC150001CpouCpoDtlCache getInstance() {
        NWZC150001CpouCpoDtlCache myInstance = (NWZC150001CpouCpoDtlCache) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWZC150001CpouCpoDtlCache();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    };

    private NWZC150001CpouCpoDtlCache() {
        super();
    }

    public CPO_DTLTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (CPO_DTLTMsgArray) super.getTMsgArray(CPO_DTLTMsg.class, findCondition, tMsgArrayCache);
    }

    public CPO_DTLTMsg getTMsgByKey(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(cpoOrdNum).append(",");
        sb.append(cpoDtlLineNum).append(",");
        sb.append(cpoDtlLineSubNum);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final CPO_DTLTMsg reqTMsg = new CPO_DTLTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
            reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
            reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (CPO_DTLTMsg) resTMsg;
    }

}
