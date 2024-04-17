/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2620;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * AR Refund Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4759
 * 2016/04/20   Hitachi         T.Tsuchida      Update          QC#7249
 * 2016/05/19   Hitachi         T.Tsuchida      Update          QC#7841
 * 2018/08/09   Hitachi         E.Kameishi      Update          QC#27462
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/07/27   Hitachi         A.Kohinata      Update          QC#57418
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 * 2023/06/15   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public final class NFCL2620Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFCL2620Query INSTANCE = new NFCL2620Query();

    /**
     * Constructor.
     */
    private NFCL2620Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFCL2620Query
     */
    public static NFCL2620Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NFCL2620CMsg
     * @param sMsg NFCL2620SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    // START 2023/06/15 S.Fujita [QC#61486,MOD]
    // public S21SsmEZDResult getSearchData(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, int cnt) {
    public S21SsmEZDResult getSearchData(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, int cnt, boolean isCollectionReps) {
        // return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt), sMsg.A);
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, cnt, isCollectionReps), sMsg.A);
    // END 2023/06/15 S.Fujita [QC#61486,MOD]
    }

    /**
     * getSsmParam
     * @param cMsg NFCL2620CMsg
     * @param sMsg NFCL2620SMsg
     * @param limitCount int
     * @return Map<String, Object>
     */
    // START 2023/06/15 S.Fujita [QC#61486,MOD]
    // public static Map<String, Object> getSsmParam(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, int limitCount) {
    public static Map<String, Object> getSsmParam(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, int limitCount, boolean isCollectionReps) {
    // END 2023/06/15 S.Fujita [QC#61486,MOD]
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("arRfRqstPk", cMsg.arRfRqstPk_H.getValue());
        // START 2023/06/15 S.Fujita [QC#61486,MOD]
        if (isCollectionReps) {
            params.put("arRfTpCd", AR_RF_TP.CUSTOMER);
        } else{
            params.put("arRfTpCd", cMsg.arRfTpCd_H.getValue());
        }
        // END 2023/06/15 S.Fujita [QC#61486,MOD]
        params.put("billToCustAcctCd", cMsg.billToCustAcctCd_H.getValue());
        params.put("arRfStsCd", cMsg.arRfStsCd_H.getValue());
        params.put("arRfCratDtFrom", cMsg.arRfCratDt_F.getValue());
        params.put("arRfCratDtTo", cMsg.arRfCratDt_T.getValue());
        // add start 2022/07/20 QC#60113
        params.put("apPmtChkNum", cMsg.apPmtChkNum_H.getValue());
        // add end 2022/07/20 QC#60113
        // add start 2022/08/01 QC#60113-1
        params.put("dealRfAmt", cMsg.dealRfAmt_H.getValue());
        // add end 2022/08/01 QC#60113-1
        // START 2018/08/09 E.Kameishi [QC#27462,ADD]
        params.put("pending", AR_DS_WF_STS.PENDING);
        // END 2018/08/09 E.Kameishi [QC#27462,ADD]
        params.put("limitCount", limitCount);
        return params;
    }

    /**
     * @param bizMsg NFCL2610CMsg
     * @return SELL_TO_CUSTTMsgArray
     */
    public static SELL_TO_CUSTTMsgArray findBillToAcctCust(NFCL2620CMsg bizMsg) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("sellToCustCd01", bizMsg.billToCustAcctCd_H.getValue());
        inTMsg.setMaxCount(0);
        inTMsg.setSQLID("001");
        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outMsg;
    }

    /**
     * getRfStsList
     * @param cMsg NFCL2620CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRfStsList(NFCL2620CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("arDsWfStsCdList", getArDsWfStsCdList());
        ssmParam.put("arRfStsCdList", getArRfStsCdList());
        return getSsmEZDClient().queryObjectList("getRfStsList", ssmParam);
    }

    private List<String> getArDsWfStsCdList() {
        List<String> arDsWfStsCdList = new ArrayList<String>();
        arDsWfStsCdList.add(AR_DS_WF_STS.PENDING);
        arDsWfStsCdList.add(AR_DS_WF_STS.REJECTED);
        return arDsWfStsCdList;
    }

    private List<String> getArRfStsCdList() {
        List<String> arRfStsCdList = new ArrayList<String>();
        arRfStsCdList.add(AR_RF_STS.ERROR);
        arRfStsCdList.add(AR_RF_STS.CREATED);
        arRfStsCdList.add(AR_RF_STS.AP_INOVICE_CREATED);
        arRfStsCdList.add(AR_RF_STS.CLOSED);
        // add start 2022/07/27 QC#57418
        arRfStsCdList.add(AR_RF_STS.SAVED);
        // add end 2022/07/27 QC#57418
        return arRfStsCdList;
    }

    // START 2023/06/15 S.Fujita [QC#61486,ADD]
    /**
     * getPulldownArRfTpCd
     * @param glblCmpyCd String
     * @param arRfTpCdCreditCardRefund String
     * @param isCollectionReps boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPulldownArRfTpCd(String glblCmpyCd, String arRfTpCdCreditCardRefund, boolean isCollectionReps) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arRfTpCdCreditCardRefund", arRfTpCdCreditCardRefund);
        if (isCollectionReps) {
            ssmParam.put("arRfTpCdCustomerRefund", AR_RF_TP.CUSTOMER);
        }
        return getSsmEZDClient().queryObjectList("getPulldownArRfTpCd", ssmParam);
    }
    // END 2023/06/15 S.Fujita [QC#61486,ADD]
}
