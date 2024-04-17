package business.blap.NFCL3030;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFCL3030.constant.NFCL3030Constant;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 10/05/2015   Fujitsu         T.Tanaka        Update          implements NFDL0010Constant -> NFCL3030Constant
 * 04/25/2016   Fujitsu         S.Fujita        Update          QC#5671
 * 2016/07/25   Hitachi         J.Kim           Update          QC#9476
 * 2016/08/23   Hitachi         T.Tsuchida      Update          QC#13570
 * 2016/11/11   Hitachi         J.Kim           Update          QC#15756
 * 2018/01/19   Fujitsu         H.Ikeda         Update          QC#23136
 * 2018/02/06   Fujitsu         T.Murai         Update          QC#21372
 * 2018/10/09   Fujitsu         T.Ogura         Update          QC#28166
 * 2019/08/26   Fujitsu         H.Ikeda         Update          QC#52885
 * 2020/09/04   CITS            R.Kurahashi     CITS            QC#56012-3
 * 2022/01/17   CITS            G.Delgado       Update          QC#58844
 * 2022/04/26   CITS            K.Suzuki        Update          QC#59333
 * 2022/05/13   CITS            D.Mamaril       Update          QC#59333
 * 2022/08/04   CITS            D.Mamaril       Update          QC#60376
 * 2022/08/31   CITS            K.Suzuki        Update          QC#60510
 *</pre>
 */
public class NFCL3030Query extends S21SsmEZDQuerySupport implements NFCL3030Constant {

    /**
     * Singleton instance.
     */
    private static final NFCL3030Query myInstance = new NFCL3030Query();

    /**
     * Constructor
     */
    public NFCL3030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0010Query
     */
    public static NFCL3030Query getInstance() {
        return myInstance;
    }

    /**
     * Get Receipt
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcpt(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsg("getRcpt", ssmParam, bizMsg);
    }

    /**
     * Get Refund Receipt Count
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRefundRcptCount(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRefundRcptCount", ssmParam);
    }

    /**
     * Get Reversed AR Cash Application Primary Key
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReversedArCashAppPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getReversedArCashAppPk", ssmParam);
    }

    /**
     * Get Reversed Cash Application Information
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReversedCashApplicationInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getReversedCashApplicationInfo", ssmParam);
    }

    /**
     * Get Reversed Receipt
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReversedRcpt(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getReversedRcpt", ssmParam);
    }

    /**
     * Get AR Receipt Source Pull down List
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcptSrcPullDownList(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptSrcPullDownList", ssmParam);
    }

    /**
     * Get Bank Pull down List 
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankPullDownList(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankPullDownList", ssmParam);
    }

    /**
     * Get Bank Branch Pull down List
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankBrPullDownList(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankBrPullDownList", ssmParam);
    }

    /**
     * Get Bank Account Pull down List
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankAccountPullDownList(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBankAccountPullDownList", ssmParam);
    }

    /**
     * Get Account Name
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNm(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctNm", ssmParam);
    }

    /**
     * 
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocNm(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getLocNm", ssmParam);
    }

    /**
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult geArRcptTrxTpPullDownList(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptTrxTpPullDownList", ssmParam);
    }

    /**
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptTotalAmount(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getRcptTotalAmount", ssmParam);
    }

    /**
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcptUnApply(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptUnApply", ssmParam);
    }

    // START 2016/04/25 S.Fujita [QC#5671,MOD]
    /**
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isReceiptDate(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("isReceiptDate", ssmParam);
    }
    // END 2016/04/25 S.Fujita [QC#5671,MOD]

    // START 2016/08/23 T.Tsuchida [QC#13570,ADD]
    /**
     * existDsCustBankAcctReln
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult existDsCustBankAcctReln(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("existDsCustBankAcctReln", ssmParam);
    }
    // END 2016/08/23 T.Tsuchida [QC#13570,ADD]

    // START 2016/07/25 J.Kim [QC#9476,ADD]
    /**
     * getRefundInfo
     * @param bizMsg NFCL3030CMsg
     * @param glblMsg NFCL3030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRefundInfo(NFCL3030CMsg bizMsg, NFCL3030SMsg glblMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmMap.put("rcptNum", bizMsg.rcptNum_H.getValue());
        return getSsmEZDClient().queryEZDMsg("getRefundInfo", ssmMap, glblMsg);
    }

    /**
     * getArRfRqstPk
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRfRqstPk(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("arTrxNum", bizMsg.rcptNum_H.getValue());
        // START 2022/05/13 D.Mamaril [QC#59333,ADD]
        param.put("arDsWfStsCd", AR_DS_WF_STS.REJECTED);
        // END 2022/05/13 D.Mamaril [QC#59333,ADD]
        return getSsmEZDClient().queryObject("getArRfRqstPk", param);
    }

    // START 2016/11/11 J.Kim [QC#15716,ADD]
    /**
     * getCreditCardRefundArRfRqstInfo
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditCardRefundArRfRqstInfo(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("arTrxNum", bizMsg.rcptNum_H.getValue());
        param.put("arRfTpCd", AR_RF_TP.CREDIT_CARD_REFUND);
        // START 2022/05/13 D.Mamaril [QC#59333,ADD]
        param.put("arDsWfStsCd", AR_DS_WF_STS.REJECTED);
        // END 2022/05/13 D.Mamaril [QC#59333,ADD]
        return getSsmEZDClient().queryObject("getArRfRqstPk", param);
    }
    // END 2016/11/11 J.Kim [QC#15756,ADD]

    /**
     * getPrntVnd
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVnd(NFCL3030CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsAcctNum", bizMsg.payerCustCd_H.getValue());
        ssmParam.put("prntVndTpCd", PRNT_VND_TP.ARREFUND);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return getSsmEZDClient().queryObject("getPrntVnd", ssmParam);
    }

    /**
     * getAuthPsn
     * @param bizMsg NFCL3030CMsg
     * @param userId String
     * @return String
     */
    public String getAuthPsn(NFCL3030CMsg bizMsg, String userId) {

        AUTH_PSNTMsg authPsnTMsg = new AUTH_PSNTMsg();
        authPsnTMsg.setSQLID("053");
        authPsnTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        authPsnTMsg.setConditionValue("usrNm01", userId);
        AUTH_PSNTMsgArray outMsg = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(authPsnTMsg);
        if (outMsg != null && outMsg.getValidCount() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append(outMsg.no(0).lastNm.getValue());
            sb.append(",");
            sb.append(outMsg.no(0).firstNm.getValue());
            return sb.toString();
        }
        return null;
    }
    // END 2016/07/25 J.Kim [QC#9476,ADD]
    
    // START 2018/01/19 H.Ikeda [QC#23136,ADD]
    /**
     * Get Rcpt Num Data List
     * @param glblCmpyCd String
     * @param rcptNum    String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRcptNumDataList(String glblCmpyCd, String rcptNum) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rcptNum", rcptNum);
        // START 2019/08/26 H.Ikeda [QC#52885, ADD]
        ssmParam.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        // END   2018/08/26 H.Ikeda [QC#52885, ADD]
        return getSsmEZDClient().queryObjectList("getRcptNumDataList", ssmParam);
    }

    /**
     * Get AR Cash App Data Count
     * @param glblCmpyCd String
     * @param rcptNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArCashAppDataCount(String glblCmpyCd, String rcptNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rcptNum", rcptNum);
        return getSsmEZDClient().queryObject("getArCashAppDataCount", ssmParam);
    }
    // END   2018/01/19 H.Ikeda [QC#23136,ADD]

    // START 2018/02/05 [QC#21372,ADD]
    /**
     * Get Customer Code/Name Location
     * @param glblCmpyCd String
     * @param ssmParam Map<String, Object
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustomer(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCustomer", ssmParam);
    }
    /**
     * Get Account Name like Search
     * @param bizMsg NFCL3030CMsg
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNm_Like(NFCL3030CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsAcctNm_Like", ssmParam);
    }
    // END 2018/02/05 [QC#21372,ADD]

    // START 2018/10/09 T.Ogura [QC#28166,ADD]
    /**
     * getApplyingRefundCount
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApplyingRefundCount(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("rcptNum", bizMsg.rcptNum_H.getValue());
        // START 2022/05/10 K.Suzuki [QC#59333,ADD]
        param.put("notArDsWfStsCd", AR_DS_WF_STS.REJECTED);
        // END   2022/05/10 K.Suzuki [QC#59333,ADD]
        return getSsmEZDClient().queryObject("getApplyingRefundCount", param);
    }
    // END   2018/10/09 T.Ogura [QC#28166,ADD]
    
    // START 2020/09/04 R.Kurahashi [QC#56012-3,ADD]
    /**
     * getArCashAppList
     * @param glblCmpyCd String
     * @param rcptNum    String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArCashAppList(String glblCmpyCd, String rcptNum) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rcptNum", rcptNum);
        return getSsmEZDClient().queryObjectList("getArCashAppList", ssmParam);
    }
    // END 2020/09/04 R.Kurahashi [QC#56012-3,ADD]

    // START 2022/01/17 G.Delgado [QC#58844, ADD]
    /**
     * Get BILL_TO_CUST_CD for receipt
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustCd(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("rcptNum", bizMsg.rcptNum_H.getValue());
        return getSsmEZDClient().queryObject("getBillToCustCd", param);
    }
    // END 2022/01/17 G.Delgado [QC#58844, ADD]

    // START 2022/04/26 K.Suzuki [QC#59333,ADD]
    /**
     * getApplyingOnAccountRefundCount
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApplyingOnAccountRefundCount(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("rcptNum", bizMsg.rcptNum_H.getValue());
        param.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        param.put("arCashApplyStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        param.put("arCashApplyStsP", AR_CASH_APPLY_STS.PARTIAL);
        param.put("arApplyTpCd", AR_APPLY_TP.ADJUSTMENT);
        param.put("arAdjTrxTpCd", AR_ADJ_TRX_TP.ON_ACCOUNT);
        param.put("arTrxTpCd", AR_TRX_TP.ON_ACCOUNT);
        param.put("notArDsWfStsCd", AR_DS_WF_STS.REJECTED);
        return getSsmEZDClient().queryObject("getApplyingOnAccountRefundCount", param);
    }
    // END   2022/04/26 K.Suzuki [QC#59333,ADD]

    // START 2022/08/04 D.Mamaril [QC#60376,ADD]
    /**
     * getOnAccountRefundCount
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOnAccountRefundCount(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("rcptNum", bizMsg.rcptNum_H.getValue());
        param.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        param.put("arApplyTpCd", AR_APPLY_TP.REFUND);
        param.put("arAdjTpCd", AR_ADJ_TP.A_OR_R_CASH_REFUND);
        param.put("arTrxTpCd", AR_TRX_TP.ON_ACCOUNT);
        param.put("arCashApplyStsCd", AR_CASH_APPLY_STS.VOID);
        return getSsmEZDClient().queryObject("getOnAccountRefundCount", param);
    }
    // END 2022/08/04 D.Mamaril [QC#60376,ADD]

    // START 2022/08/31 [QC#60510,ADD]
    /**
     * getCreditCardRefundArRfRqstPk
     * @param bizMsg NFCL3030CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditCardRefundArRfRqstPk(NFCL3030CMsg bizMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("arTrxNum", bizMsg.rcptNum_H.getValue());
        param.put("arRfTpCd", AR_RF_TP.CREDIT_CARD_REFUND);
        return getSsmEZDClient().queryObject("getCreditCardRefundArRfRqstPk", param);
    }
    // END 2022/08/31 [QC#60510,ADD]
}
