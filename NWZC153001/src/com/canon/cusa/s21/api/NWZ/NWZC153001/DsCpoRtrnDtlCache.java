package com.canon.cusa.s21.api.NWZ.NWZC153001;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;

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
class DsCpoRtrnDtlCache extends LocalDataCacheBase {

    /** TMsg Cache */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** TMsgArray Cache*/
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    DS_CPO_RTRN_DTLTMsgArray getTMsgArray(FindCondition findCondition) {

        return (DS_CPO_RTRN_DTLTMsgArray) super.getTMsgArray(DS_CPO_RTRN_DTLTMsg.class, findCondition, tMsgArrayCache);
    }

    DS_CPO_RTRN_DTLTMsg getTMsgByKey(String glblCmpyCd, String cpoOrdNum, String cpoRtrnDtlLineNum, String cpoRtrnDtlLineSubNum) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(cpoOrdNum).append(",");
        sb.append(cpoRtrnDtlLineNum).append(",");
        sb.append(cpoRtrnDtlLineSubNum);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final DS_CPO_RTRN_DTLTMsg reqTMsg = new DS_CPO_RTRN_DTLTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
            reqTMsg.dsCpoRtrnLineNum.setValue(cpoRtrnDtlLineNum);
            reqTMsg.dsCpoRtrnLineSubNum.setValue(cpoRtrnDtlLineSubNum);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (DS_CPO_RTRN_DTLTMsg) resTMsg;
    }

}
