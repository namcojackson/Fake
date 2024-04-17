/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1780.common;

import static business.blap.NWAL1780.constant.NWAL1780Constant.BIZ_ID;
import static business.blap.NWAL1780.constant.NWAL1780Constant.MSG_PARAM_CATG;
import static business.blap.NWAL1780.constant.NWAL1780Constant.NWAM0007W;
import static business.blap.NWAL1780.constant.NWAL1780Constant.NWAM0181E;
import static business.blap.NWAL1780.constant.NWAL1780Constant.NWAM0746E;
import static business.blap.NWAL1780.constant.NWAL1780Constant.NWAM0754E;
import static business.blap.NWAL1780.constant.NWAL1780Constant.SCRN_ID_00;
import static business.blap.NWAL1780.constant.NWAL1780Constant.ZZZM9001E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWAL1780.NWAL1780CMsg;
import business.blap.NWAL1780.NWAL1780Query;
import business.blap.NWAL1780.NWAL1780SMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * Supply Quote Search
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         T.Murai         Create          N/A
 * 2016/09/09   Hitachi         T.Mizuki        Update          QC#14242
 * 2018/03/02   Fujitsu         K.Ishizuka      Update          QC#22956
 *</pre>
 */
public class NWAL1780CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown
     * @param bizMsg NWAL1780CMsg
     */
    public static void createPulldown(NWAL1780CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(SPLY_QUOTE_STS.class, bizMsg.splyQuoteStsCd_CD, bizMsg.splyQuoteStsDescTxt_NM);

        createRsnPullDown(bizMsg);
    }

    /**
     * Create Reason Code Pulldown
     * @param bizMsg NWAL1780CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createRsnPullDown(NWAL1780CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1780Query.getInstance().getDsOrdTpTxt(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }
        }
    }

    /**
     * Create Reason Code Pulldown
     * @param bizMsg NWAL1780CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createRsnPullDownForCatg(NWAL1780CMsg bizMsg) {

        bizMsg.dsOrdTpCd_CD.clear();
        bizMsg.dsOrdTpDescTxt_NM.clear();
        S21SsmEZDResult ssmResult = NWAL1780Query.getInstance().getDsOrdTpTxt(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }
        }
    }

    /**
     * Get Quote Data
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     * @return boolean
     */
    public static boolean getQuote(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NWAL1780Query.getInstance().getQuote(bizMsg, glblMsg);

        if (!ssmResult.isCodeNotFound()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NWAM0007W);
            }

            setResult(ssmResult, glblMsg);

        } else {
            bizMsg.setMessageInfo(ZZZM9001E);
            return false;
        }

        return true;
    }

    /**
     * Check Input Category
     * @param bizMsg NWAL1780CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static boolean checkExistCatg(NWAL1780CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1780Query.getInstance().getDsOrdCatgList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, String>> dsOrdCatgCdList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (dsOrdCatgCdList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_DESC_TXT"));

        return true;
    }

    /**
     * Validation Check
     * @param scrnMsg NWAL1780CMsg
     * @return boolean
     */
    public static boolean searchValidCheck(NWAL1780CMsg scrnMsg) {

        if (!hasInput(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0754E);
            return false;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.splyQuoteDt_TO)) {
            if (scrnMsg.splyQuoteDt_FR.getValue().compareTo(scrnMsg.splyQuoteDt_TO.getValue()) > 0) {
                scrnMsg.splyQuoteDt_FR.setErrorInfo(1, NWAM0746E);
                scrnMsg.splyQuoteDt_TO.setErrorInfo(1, NWAM0746E);
                return false;
            }
        }

        return true;
    }

    /**
     * @param scrnMsg
     * @return boolean
     */
    private static boolean hasInput(NWAL1780CMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteNum)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.soldToCustLocCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_SO)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_SI)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteDt_FR)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteDt_TO)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteStsCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.adminPsnCd)) {
            return true;
        }
        // 2018/03/02 S21_NA#22956 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteNm)) {
            return true;
        }
        // 2018/03/02 S21_NA#22956 Add End

        return false;
    }

    /**
     * Set Result Quote Data
     * @param ssmResult S21SsmEZDResult
     * @param glblMsg NWAL1780SMsg
     */
    @SuppressWarnings("unchecked")
    public static void setResult(S21SsmEZDResult ssmResult, NWAL1780SMsg glblMsg) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int i = 0;
        for (; i < resultList.size(); i++) {

            if (i == glblMsg.A.length()) {
                break;
            }

            Map<String, Object> map = resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyQuoteNum_A, (String) map.get("SPLY_QUOTE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).cpoOrdNum_A, (String) map.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).sellToCustCd_A, (String) map.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).soldToCustLocCd_A, (String) map.get("SOLD_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsAcctNm_AO, (String) map.get("SOLD_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxAllLineAddr_SO, (String) map.get("SOLD_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustAcctCd_A, (String) map.get("SHIP_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustCd_A, (String) map.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsAcctNm_AI, (String) map.get("SHIP_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxAllLineAddr_SI, (String) map.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdCatgDescTxt_A, (String) map.get("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsOrdTpDescTxt_A, (String) map.get("DS_ORD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyQuoteDt_A, (String) map.get("SPLY_QUOTE_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyQuoteStsDescTxt_A, (String) map.get("SPLY_QUOTE_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).custIssPoNum_A, (String) map.get("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxPsnNm_A, (String) map.get("CREATED_BY"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).splyQuoteNm_A, (String) map.get("SPLY_QUOTE_NM")); // 2018/03/02 S21_NA#22956 Add
        }
        glblMsg.A.setValidCount(i);
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg, String srchOptUsrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * callNszc0330
     * @param bizMsg NWAL1780CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NWAL1780CMsg bizMsg, NSZC033001PMsg pMsg) {

        // Search Option API(NSZC0330) is executed
        new NSZC033001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NWAL1780CMsg
     * @param srchOptUsrId String
     */
    @SuppressWarnings("unchecked")
    public static void createSavedSearchOptionsPullDown(NWAL1780CMsg bizMsg, String srchOptUsrId) {

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        S21SsmEZDResult ssmResult = NWAL1780Query.getInstance().getSavedSearchOptionList(srchOptUsrId);

        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NWAL1780CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NWAL1780CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }

            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.splyQuoteNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.sellToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.soldToCustLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.dsAcctNm_SO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.shipToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.dsAcctNm_SI.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.dsOrdTpCd.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.splyQuoteDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.splyQuoteDt_TO.getValue());
        }
        // mod start 2016/09/09 CSA QC#14242
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.splyQuoteStsCd.getValue());
        // mod end 2016/09/09 CSA QC#14242
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.custIssPoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.adminPsnCd.getValue());
        // mod start 2016/09/09 CSA QC#14242
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.dsOrdCatgDescTxt.getValue());
        // mod end 2016/09/09 CSA QC#14242
        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    private static boolean isSameSaveSearchName(NWAL1780CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }

            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    private static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NWAL1780CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }

            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NWAL1780CMsg
     * @param glblMsg NWAL1780SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NWAL1780CMsg bizMsg, NWAL1780SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_SO, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_SI, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, pMsg.srchOptTxt_10);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteDt_FR, pMsg.srchOptTxt_11.getValue());
        } else {
            bizMsg.splyQuoteDt_FR.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_12.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteDt_TO, pMsg.srchOptTxt_12.getValue());
        } else {
            bizMsg.splyQuoteDt_TO.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteStsCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.adminPsnCd, pMsg.srchOptTxt_15);
        // mod start 2016/09/09 CSA QC#14242
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, pMsg.srchOptTxt_16);
        // mod end 2016/09/09 CSA QC#14242
    }
}
