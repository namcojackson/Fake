package com.canon.cusa.s21.api.NWZ.NWZC155001;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.DS_CPO_RTRN_DTL_VTMsg;
import business.db.DS_CPO_RTRN_DTL_VTMsgArray;

import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
class DsCpoRtrnDtlVCache extends LocalDataCacheBase {

    /** TMsg Cache */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** TMsgArray Cache */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    DS_CPO_RTRN_DTL_VTMsgArray getTMsgArray(FindCondition findCondition) {

        return (DS_CPO_RTRN_DTL_VTMsgArray) super.getTMsgArray(DS_CPO_RTRN_DTL_VTMsg.class, findCondition, tMsgArrayCache);
    }

    DS_CPO_RTRN_DTL_VTMsg getTMsgByKey(String glblCmpyCd, String cpoOrdNum, String dsCpoRtrnLineNum, String dsCpoRtrnLineSubNum) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(cpoOrdNum).append(",");
        sb.append(dsCpoRtrnLineNum).append(",");
        sb.append(dsCpoRtrnLineSubNum);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final DS_CPO_RTRN_DTL_VTMsg reqTMsg = new DS_CPO_RTRN_DTL_VTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
            reqTMsg.dsCpoRtrnLineNum.setValue(dsCpoRtrnLineNum);
            reqTMsg.dsCpoRtrnLineSubNum.setValue(dsCpoRtrnLineSubNum);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (DS_CPO_RTRN_DTL_VTMsg) resTMsg;
    }

}
