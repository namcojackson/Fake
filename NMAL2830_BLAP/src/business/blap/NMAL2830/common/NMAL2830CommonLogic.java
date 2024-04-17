/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2830.common;

import static business.blap.NMAL2830.constant.NMAL2830Constant.BIZ_ID;
import static business.blap.NMAL2830.constant.NMAL2830Constant.SCRN_ID_00;
import static business.blap.NMAL2830.constant.NMAL2830Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2830.NMAL2830CMsg;
import business.blap.NMAL2830.NMAL2830Query;
import business.blap.NMAL2830.NMAL2830SMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL2830CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NMAL2830CMsg
     * @param glblMsg NMAL2830SMsg
     * @param srchOptUsrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NMAL2830CMsg
     * @param glblMsg NMAL2830SMsg
     * @param srchOptUsrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.xxFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.xxToDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.dsXrefAcctCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.xxAllLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.fill65Txt_RN);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.psnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.locNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.orgNm_TN);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.orgNm_ON);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.stCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.xxTpCd_D);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.xxChkBox_RT);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.postCd);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    private static boolean isSameSaveSearchName(NMAL2830CMsg bizMsg) {
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

    /**
     * isExistSaveSearchName
     * @param bizMsg NMAL2830CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL2830CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL2830CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL2830CMsg bizMsg) {
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
        return;
    }

    private static boolean callNszc0330(NMAL2830CMsg bizMsg, NSZC033001PMsg pMsg) {
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
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;

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

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL2830CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NMAL2830CMsg bizMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NMAL2830Query.getInstance().getSavedSearchOptionList(srchOptUsrId);

        if (!ssmResult.isCodeNormal()) {

            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NMAL2830CMsg
     * @param glblMsg NMAL2830SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFromDt, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxToDt, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsXrefAcctCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.fill65Txt_RN, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnCd, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_TN, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_ON, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.stCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_D, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RT, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.postCd, pMsg.srchOptTxt_16);
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL2830CMsg
     * @param glblMsg NMAL2830SMsg
     */
    public static void updateGlblMsg(NMAL2830CMsg bizMsg, NMAL2830SMsg glblMsg) {

        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        glblMsg.clearErrorInfo();
        glblMsg.A.clearErrorInfo();

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * getErrRow
     * @param errRow int
     * @param setRow int
     * @return int
     */
    public static int getErrRow(int errRow, int setRow) {

        if (errRow < 0) {
            errRow = setRow;
        }

        return errRow;
    }
}
