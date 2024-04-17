package com.canon.cusa.s21.api.NWZ.NWZC153001;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;

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
class CpoDtlCache extends LocalDataCacheBase {

    /** TMsg Cache */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** TMsgArray Cache */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    CPO_DTLTMsgArray getTMsgArray(FindCondition findCondition) {

        return (CPO_DTLTMsgArray) super.getTMsgArray(CPO_DTLTMsg.class, findCondition, tMsgArrayCache);
    }

    CPO_DTLTMsg getTMsgByKey(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

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
