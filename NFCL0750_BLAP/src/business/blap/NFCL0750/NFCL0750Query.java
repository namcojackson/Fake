/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0750;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL0750.constant.NFCL0750Constant;
import business.db.AR_ADJ_TPTMsg;
import business.db.AR_ADJ_TPTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;


import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/06   Hitachi         K.Kojima        Update          QC#11025
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/04/20   Hitachi         J.Kim           Update          QC#24885
 *</pre>
 */
public final class NFCL0750Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL0750Query INSTANCE = new NFCL0750Query();

    /**
     * Constructor.
     */
    private NFCL0750Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL0750Query
     */
    public static NFCL0750Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NFCL0750CMsg
     * @param sMsg NFCL0750SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NFCL0750CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NFCL0750CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", cMsg);
        params.put("wrtOffOptTpCdIsGenerateOnly", NFCL0750Constant.WRT_OFF_OPT_TP_IS_GEN_ONLY);
        params.put("wrtOffOptTpCdIsCreateAdj", NFCL0750Constant.WRT_OFF_OPT_TP_IS_CRAT_ADJ);
        // START 2016/07/06 K.Kojima [QC#11025,ADD]
        params.put("wrtOffOptTpCdIsGenerateOnlyNm", NFCL0750Constant.WRT_OFF_OPT_TP_IS_GEN_ONLY_NM);
        params.put("wrtOffOptTpCdIsCreateAdjNm", NFCL0750Constant.WRT_OFF_OPT_TP_IS_CRAT_ADJ_NM);
        // START 2018/04/19 J.Kim [QC#24885,MOD]
        params.put("myGroupId", cMsg.getUserID());
        if (ZYPCommonFunc.hasValue(cMsg.wrtOffRqstUsrId_H)) {
            params.put("userID", cMsg.wrtOffRqstUsrId_H.getValue());
        } else {
            params.put("userID", cMsg.getUserID());
        }
        // END 2018/04/19 J.Kim [QC#24885,MOD]
        // END 2016/07/06 K.Kojima [QC#11025,ADD]
        // START 2017/08/21 T.Tsuchida [QC#19573,DEL]
        //params.put("dateFormat", NFCL0750Constant.DATE_FORMAT_YYYYMMDD);
        // END 2017/08/21 T.Tsuchida [QC#19573,DEL]
        params.put("limitCnt", cnt);
        return params;
    }

    /**
     * getArAdjTpList
     * @param cMsg NFCL0750CMsg
     * @return AR_ADJ_TPTMsgArray
     */
    public AR_ADJ_TPTMsgArray getArAdjTpList(NFCL0750CMsg cMsg) {
        AR_ADJ_TPTMsg tMsg = new AR_ADJ_TPTMsg();
        tMsg.setSQLID("011");
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("arAdjCatgCd01", AR_ADJ_CATG.ADJUSTMENT);
        return (AR_ADJ_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    /**
     * getSellToCust
     * @param cMsg NFCL0750CMsg
     * @return SELL_TO_CUSTTMsg
     */
    public SELL_TO_CUSTTMsg getSellToCust(NFCL0750CMsg cMsg) {
        SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H.getValue());
        SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() == 0) {
            return null;
        }
        return (SELL_TO_CUSTTMsg) tMsgArray.no(0);
    }

    /**
     * existSellToCust
     * @param cMsg NFCL0750CMsg
     * @return boolean
     */
    public boolean existSellToCust(NFCL0750CMsg cMsg) {
        SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H.getValue());
        int count = EZDTBLAccessor.count(tMsg);
        if (count > 0) {
            return true;
        }
        return false;
    }

}
