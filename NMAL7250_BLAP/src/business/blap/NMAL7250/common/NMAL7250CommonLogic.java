/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7250.common;

import static business.blap.NMAL7250.constant.NMAL7250Constant.BIZ_ID;
import static business.blap.NMAL7250.constant.NMAL7250Constant.SCRN_ID_00;
import static business.blap.NMAL7250.constant.NMAL7250Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NMAL7250.NMAL7250CMsg;
import business.blap.NMAL7250.NMAL7250Query;
import business.blap.NMAL7250.NMAL7250SMsg;
import business.parts.NSZC033001PMsg;

/**
 *<pre>
 * NMAL7250CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Fujitsu         W.Honda         Create          N/A
 * 2016/06/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9173
 * 2018/04/04   Fujitsu         R.Nakamura      Update          S21_NA#25206
 *</pre>
 */
public class NMAL7250CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL7250CMsg bizMsg = (NMAL7250CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    private static boolean isSameSaveSearchName(NMAL7250CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L1.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk.getValue().compareTo(cMsg.srchOptPk_L1.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm.getValue().equals(cMsg.srchOptNm_L1.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NMAL7250CMsg
     * @param glblMsg NMAL7250SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NMAL7250CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL7250CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L1.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L1.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk) //
                        && bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L1.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * callNszc0330forSaveSearch.
     * @param bizMsg NMAL7250CMsg
     * @param glblMsg NMAL7250SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.prcRuleNm);
        // QC#9173 2016/06/01 Add Start
        // if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdGrpNum)) {
        //     ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.prcRulePrcdGrpNum.getValue().toString());
        // }
        if (ZYPCommonFunc.hasValue(bizMsg.defRulePrcdNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.defRulePrcdNum.getValue().toString());
        }
        // QC#9173 2016/06/01 Add End
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.prcRuleCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.prcRuleTrxCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.prcRuleAttrbCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.prcRuleCondFromTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.csmpContrNum);
        if (ZYPCommonFunc.hasValue(bizMsg.prcGrpPk_CG)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.prcGrpPk_CG.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.lineBizTpCd);
        if (ZYPCommonFunc.hasValue(bizMsg.prcGrpPk_MA)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.prcGrpPk_MA.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.effFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.effThruDt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.prcDispRecTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.prcRuleCondTpCd);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk, pMsg.srchOptPk);
            bizMsg.srchOptNm.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL7250CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL7250CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L1.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L1.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L1.no(i));
            }
        }
        return;
    }

    private static boolean callNszc0330(NMAL7250CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
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
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL7250CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL7250CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL7250Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L1.clear();
            bizMsg.srchOptNm_L1.clear();
            return;
        }

        bizMsg.srchOptPk_L1.clear();
        bizMsg.srchOptNm_L1.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L1.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L1.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L1.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NMAL7250CMsg
     * @param glblMsg NMAL7250SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL7250CMsg bizMsg, NMAL7250SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleNm, pMsg.srchOptTxt_01);
        // QC#9173 2016/06/01 Mod Start
        // if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_02)) {
        //     ZYPEZDItemValueSetter.setValue(bizMsg.prcRulePrcdGrpNum, new BigDecimal(pMsg.srchOptTxt_02.getValue()));
        // }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_02)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.defRulePrcdNum, new BigDecimal(pMsg.srchOptTxt_02.getValue()));
        }
        // QC#9173 2016/06/01 Mod End
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleCatgCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleTrxCatgCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleCondFromTxt, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, pMsg.srchOptTxt_11);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_12)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpPk_CG, new BigDecimal(pMsg.srchOptTxt_12.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, pMsg.srchOptTxt_14);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_15)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpPk_MA, new BigDecimal(pMsg.srchOptTxt_15.getValue()));
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_16.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, pMsg.srchOptTxt_16.getValue());
        } else {
            bizMsg.effFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_16.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt, pMsg.srchOptTxt_17.getValue());
        } else {
            bizMsg.effThruDt.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleCondTpCd, pMsg.srchOptTxt_19);

    }

    // Add Start 2018/04/04 QC#25206
    /**
     * getPullPrcRuleAttrbList
     * @param bizMsg NMAL7250CMsg
     */
    public static void getPullPrcRuleAttrbList(NMAL7250CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL7250Query.getInstance().getPullPrcRuleAttrbList();

        bizMsg.prcRuleAttrbCd_P.clear();
        bizMsg.prcRuleAttrbDescTxt_P.clear();
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.prcRuleAttrbCd_P.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbCd_P.no(i), (String) resultMap.get("PRC_RULE_ATTRB_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_P.no(i), (String) resultMap.get("PRC_RULE_ATTRB_DESC_TXT"));
        }
    }
    // Add End 2018/04/04 QC#25206
}
