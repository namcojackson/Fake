package com.canon.cusa.s21.api.NWZ.NWZC155001;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.CPO_DTL_VTMsg;
import business.db.CPO_DTL_VTMsgArray;

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
class DsCpoDtlVCache extends LocalDataCacheBase {

    /** TMsg Cache */
    private final S21LRUMap<String, EZDTMsg> tMsgCache = new S21LRUMap<String, EZDTMsg>();

    /** TMsgArray Cache */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    CPO_DTL_VTMsgArray getTMsgArray(FindCondition findCondition) {

        return (CPO_DTL_VTMsgArray) super.getTMsgArray(CPO_DTL_VTMsg.class, findCondition, tMsgArrayCache);
    }

    CPO_DTL_VTMsg getTMsgByKey(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        final StringBuilder sb = new StringBuilder();
        sb.append(glblCmpyCd).append(",");
        sb.append(cpoOrdNum).append(",");
        sb.append(cpoDtlLineNum).append(",");
        sb.append(cpoDtlLineSubNum);

        final String cacheKey = sb.toString();

        EZDTMsg resTMsg = tMsgCache.get(cacheKey);

        if (resTMsg == null) {

            // find by key
            final CPO_DTL_VTMsg reqTMsg = new CPO_DTL_VTMsg();
            reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
            reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
            reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
            reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

            resTMsg = super.findByKey(reqTMsg);
            if (resTMsg != null) {
                tMsgCache.put(cacheKey, resTMsg);
            }
        }

        return (CPO_DTL_VTMsg) resTMsg;
    }

}
