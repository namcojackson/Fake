/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2620.common;

import static business.blap.NFCL2620.constant.NFCL2620Constant.BUSINESS_ID;
import static business.blap.NFCL2620.constant.NFCL2620Constant.ERROR_MSG;
import static business.blap.NFCL2620.constant.NFCL2620Constant.NFBM0213E;
import static business.blap.NFCL2620.constant.NFCL2620Constant.NFDM0012E;
import static business.blap.NFCL2620.constant.NFCL2620Constant.NZZM0001W;
import static business.blap.NFCL2620.constant.NFCL2620Constant.ZZSM4100E;
import static business.blap.NFCL2620.constant.NFCL2620Constant.ZZZM9001E;
import static business.blap.NFCL2620.constant.NFCL2620Constant.FUNC_ID_CLT_REPS;

import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL2620.NFCL2620CMsg;
import business.blap.NFCL2620.NFCL2620Query;
import business.blap.NFCL2620.NFCL2620SMsg;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * AR Refund Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         K.Kojima        Update          CSA QC#6871
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4759
 * 2016/04/20   Hitachi         T.Tsuchida      Update          QC#7249
 * 2016/04/25   Hitachi         K.Kojima        Update          QC#7532
 * 2016/08/09   Fujitsu         C.Tanaka        Update          QC#5521
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 * 2023/06/15   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2620CommonLogic {

    /**
     * Clear Message
     * @param cMsg NFCL2620CMsg
     * @param sMsg NFCL2620SMsg
     */
    public static void clearMsg(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {

        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
    }

    /**
     * Create Pull Down
     * @param cMsg NFCL2620CMsg
     * @param boolean isCollectionReps
     */
    public static void createPullDown(NFCL2620CMsg cMsg) {
        // START 2023/06/15 S.Fujita [QC#61486,MOD]
        S21SsmEZDResult ssmResult = NFCL2620Query.getInstance().getPulldownArRfTpCd(cMsg.glblCmpyCd.getValue(), AR_RF_TP.CREDIT_CARD_REFUND, isCollectionReps());
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.arRfTpCd_C.no(i), (String) resultMap.get("AR_RF_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.arRfTpDescTxt_D.no(i), (String) resultMap.get("AR_RF_TP_DESC_TXT"));
            }
        }
        // Refund Type
        // ZYPCodeDataUtil.createPulldownList(AR_RF_TP.class, cMsg.arRfTpCd_C, cMsg.arRfTpDescTxt_D);
        // END 2023/06/15 S.Fujita [QC#61486,MOD]
        // Refund Reason
        getRfStsList(cMsg);
    }

    // START 2023/06/15 S.Fujita [QC#61486,ADD]
    public static boolean isCollectionReps() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            return false;
        }
        if (funcList.contains(FUNC_ID_CLT_REPS)) {
            return true;
        }
        return false;
    }
    // END 2023/06/15 S.Fujita [QC#61486,ADD]

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NFCL2620CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NFCL2620CMsg cMsg) {

        // mod start 2022/07/20 QC#60113
        //if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk_H) && !ZYPCommonFunc.hasValue(cMsg.arRfTpCd_H) && !ZYPCommonFunc.hasValue(cMsg.arRfStsCd_H) && !ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd_H)) {
        // mod start 2022/08/01 QC#60113-1
        //if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk_H) && !ZYPCommonFunc.hasValue(cMsg.arRfTpCd_H) && !ZYPCommonFunc.hasValue(cMsg.arRfStsCd_H) && !ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd_H) && !ZYPCommonFunc.hasValue(cMsg.apPmtChkNum_H)) {
        if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk_H) && !ZYPCommonFunc.hasValue(cMsg.arRfTpCd_H) && !ZYPCommonFunc.hasValue(cMsg.arRfStsCd_H) && !ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd_H)
                && !ZYPCommonFunc.hasValue(cMsg.apPmtChkNum_H) && !ZYPCommonFunc.hasValue(cMsg.dealRfAmt_H)) {
        // mod end 2022/08/01 QC#60113-1
        // mod end 2022/07/20 QC#60113
            cMsg.arRfRqstPk_H.setErrorInfo(1, NFBM0213E, ERROR_MSG);
            cMsg.arRfTpCd_H.setErrorInfo(1, NFBM0213E, ERROR_MSG);
            cMsg.billToCustAcctCd_H.setErrorInfo(1, NFBM0213E, ERROR_MSG);
            cMsg.arRfStsCd_H.setErrorInfo(1, NFBM0213E, ERROR_MSG);
            // add start 2022/07/20 QC#60113
            cMsg.apPmtChkNum_H.setErrorInfo(1, NFBM0213E, ERROR_MSG);
            // add end 2022/07/20 QC#60113
            // add start 2022/08/01 QC#60113-1
            cMsg.dealRfAmt_H.setErrorInfo(1, NFBM0213E, ERROR_MSG);
            // add end 2022/08/01 QC#60113-1
            return false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.arRfCratDt_F) && ZYPCommonFunc.hasValue(cMsg.arRfCratDt_T)) {
            if (cMsg.arRfCratDt_F.getValue().compareTo(cMsg.arRfCratDt_T.getValue()) > 0) {
                cMsg.setMessageInfo(ZZSM4100E);
                return false;
            }
        }
        return true;
    }

    /**
     * Get Search Data
     * @param cMsg NFCL2620CMsg
     * @param sMsg NFCL2620SMsg
     */
    // START 2023/06/15 S.Fujita [QC#61486,MOD]
    // public static void getSearchData(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg) {
    public static void getSearchData(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, boolean isCollectionReps) {
        // S21SsmEZDResult ssmResult = NFCL2620Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NFCL2620Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1, isCollectionReps);
    // END 2023/06/15 S.Fujita [QC#61486,MOD]
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length());
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * Get Refund Status
     * @param cMsg NFCL2620CMsg
     */
    private static void getRfStsList(NFCL2620CMsg cMsg) {
        S21SsmEZDResult ssmResult = NFCL2620Query.getInstance().getRfStsList(cMsg);
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size() && i < cMsg.arRfStsCd_C.length(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                cMsg.arRfStsCd_C.no(i).setValue((String) resultMap.get("AR_RF_STS_CD"));
                cMsg.arRfStsDescTxt_D.no(i).setValue((String) resultMap.get("AR_RF_STS_DESC_TXT"));
            }
        }
    }

    /**
     * Search Bill To Customer Account Name
     * @param cMsg NFCL2620CMsg
     */
    public static void searchAddressForBillToCustomerAccount(NFCL2620CMsg cMsg) {

        SELL_TO_CUSTTMsgArray outSellToCustTMsg = NFCL2620Query.findBillToAcctCust(cMsg);

        if (outSellToCustTMsg.getValidCount() == 0) {
            cMsg.billToCustAcctCd_H.setErrorInfo(1, NFDM0012E, new String[] {"Customer" });
            return;
        }
        cMsg.dsAcctNm_H.setValue(outSellToCustTMsg.no(0).dsAcctNm.getValue());
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NFCL2620CMsg
     * @param sMsg NFCL2620SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFCL2620CMsg cMsg, NFCL2620SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    // QC#5521 ADD Start
    /**
     * Create Save Option PullDown list
     * @param bizMsg NFCL2620CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NFCL2620CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BUSINESS_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_L.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_L.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NFCL2620CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NFCL2620CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NFCL2620CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFCL2620CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk) && bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NFCL2620CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NFCL2620CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NFCL2620CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_L.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }
    // QC#5521 ADD End
}
