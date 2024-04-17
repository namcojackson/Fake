/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7070.common;

import static business.blap.NMAL7070.constant.NMAL7070Constant.BIZ_ID;
import static business.blap.NMAL7070.constant.NMAL7070Constant.EVENT_SAVE_SEARCH;
import static business.blap.NMAL7070.constant.NMAL7070Constant.SCRN_ID_00;
import static business.blap.NMAL7070.constant.NMAL7070Constant.STS_ACTIVE;
import static business.blap.NMAL7070.constant.NMAL7070Constant.STS_DELETED;
import static business.blap.NMAL7070.constant.NMAL7070Constant.STS_INACTIVE;
import static business.blap.NMAL7070.constant.NMAL7070Constant.ZZZM9003I;
import static business.blap.NMAL7070.constant.NMAL7070Constant.YYYYMMDD_LENGTH;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL7070.NMAL7070CMsg;
import business.blap.NMAL7070.NMAL7070Query;
import business.blap.NMAL7070.NMAL7070SMsg;
import business.blap.NMAL7070.NMAL7070_ASMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL7070CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL7070CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL7070Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L1.clear();
            bizMsg.srchOptNm_L1.clear();
            return;
        }

        bizMsg.srchOptPk_L1.clear();
        bizMsg.srchOptNm_L1.clear();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L1.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L1.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L1.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * setSeachResult
     * @param resultList List<Map<?, ?>>
     * @param glblMsg NMAL7070SMsg
     */
    public static void setSeachResult(List<Map<?, ?>> resultList, NMAL7070SMsg glblMsg) {
        int i = 0;
        for (Map<?, ?> result : resultList) {
            NMAL7070_ASMsg glblLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnPk_A, (BigDecimal) result.get("SPLY_AGMT_PLN_PK"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnNm_A, (String) result.get("SPLY_AGMT_PLN_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnShortNm_A, (String) result.get("SPLY_AGMT_PLN_SHORT_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnDescTxt_A, (String) result.get("SPLY_AGMT_PLN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.annTermCapNum_A, (BigDecimal) result.get("ANN_TERM_CAP_NUM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnTpNm_A, (String) result.get("SPLY_AGMT_PLN_TP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtDocTpNm_A, (String) result.get("SPLY_AGMT_DOC_TP_NM"));
            String actvFlg = (String) result.get("ACTV_FLG");
            String delFlg = (String) result.get("DEL_FLG");
            String effFrom = (String) result.get("EFF_FROM_DT");
            String effThru = (String) result.get("EFF_THRU_DT");
            String slsDt = ZYPDateUtil.getSalesDate();
            if (effThru == null) {
                effThru = "99991231";
            }

            if (ZYPConstant.FLG_ON_Y.equals(delFlg)) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem8Txt_A, STS_DELETED);
            } else if (ZYPConstant.FLG_ON_Y.equals(actvFlg) //
                    && ZYPDateUtil.compare(effFrom, slsDt) <= 0 //
                    && ZYPDateUtil.compare(effThru, slsDt) >= 0) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem8Txt_A, STS_ACTIVE);
            } else {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem8Txt_A, STS_INACTIVE);
            }
            
            ZYPEZDItemValueSetter.setValue(glblLineMsg.effFromDt_A, (String) result.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.effThruDt_A, (String) result.get("EFF_THRU_DT"));
            if (result.get("EZINTIME") != null) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxDt10Dt_CD, ((String) result.get("EZINTIME")).substring(0, 8));
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxFullNm_CB, (String) result.get("CREATED_BY"));
            if (result.get("EZUPTIME") != null) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxDt10Dt_UD, ((String) result.get("EZUPTIME")).substring(0, 8));
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxFullNm_UB, (String) result.get("UPDATE_BY"));
            i++;
            if (i >= glblMsg.A.getValidCount()) {
                break;
            }
        }
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;

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

    private static boolean isSameSaveSearchName(NMAL7070CMsg cMsg) {
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
     * @param bizMsg NMAL7070CMsg
     * @param glblMsg NMAL7070SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg, String userId, String glblCmpyCd) {
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
     * @param bizMsg NMAL7070CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL7070CMsg bizMsg) {
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
     * @param bizMsg NMAL7070CMsg
     * @param glblMsg NMAL7070SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg, String usrId, String glblCmpyCd) {
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

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.splyAgmtPlnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.splyAgmtPlnShortNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.splyAgmtPlnDescTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.splyAgmtPlnTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.splyAgmtDocTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.csmpNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.lineBizTpCd);
        if (ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.effFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.effThruDt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.splyAgmtPlnStsCd);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk, pMsg.srchOptPk);
            bizMsg.srchOptNm.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, EVENT_SAVE_SEARCH) });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL7070CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL7070CMsg bizMsg) {
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

    private static boolean callNszc0330(NMAL7070CMsg bizMsg, NSZC033001PMsg pMsg) {
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
     * callNszc0330forSearchOption
     * @param bizMsg NMAL7070CMsg
     * @param glblMsg NMAL7070SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL7070CMsg bizMsg, NMAL7070SMsg glblMsg, String usrId, String glblCmpyCd) {

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

        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnNm, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnShortNm, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnDescTxt, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnTpCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtDocTpCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpNum, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, pMsg.srchOptTxt_10);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, pMsg.srchOptTxt_11.getValue());
        } else {
            bizMsg.effFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_12.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt, pMsg.srchOptTxt_12.getValue());
        } else {
            bizMsg.effThruDt.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnStsCd, pMsg.srchOptTxt_13);

    }

    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }
}
