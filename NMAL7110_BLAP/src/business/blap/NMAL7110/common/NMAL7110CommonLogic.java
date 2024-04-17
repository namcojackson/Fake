/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7110.common;

import static business.blap.NMAL7110.constant.NMAL7110Constant.BIZ_ID;
import static business.blap.NMAL7110.constant.NMAL7110Constant.SCRN_ID_00;
import static business.blap.NMAL7110.constant.NMAL7110Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL7110.NMAL7110CMsg;
import business.blap.NMAL7110.NMAL7110Query;
import business.blap.NMAL7110.NMAL7110SMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7110CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7110CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL7110CMsg bizMsg = (NMAL7110CMsg) cMsg;

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

    private static boolean isSameSaveSearchName(NMAL7110CMsg cMsg) {
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
     * @param bizMsg NMAL7110CMsg
     * @param glblMsg NMAL7110SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg, String userId, String glblCmpyCd) {
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
     * @param bizMsg NMAL7110CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL7110CMsg bizMsg) {
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
     * @param bizMsg NMAL7110CMsg
     * @param glblMsg NMAL7110SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg, String usrId, String glblCmpyCd) {
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

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.prcContrNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.assnPgmContrFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.prcContrCustBidNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.lineBizTpCd);
        if (ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.effFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.effThruDt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.actvFlg);

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
     * @param bizMsg NMAL7110CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL7110CMsg bizMsg) {
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

    private static boolean callNszc0330(NMAL7110CMsg bizMsg, NSZC033001PMsg pMsg) {
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
     * @param bizMsg NMAL7110CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL7110CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL7110Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
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
     * @param bizMsg NMAL7110CMsg
     * @param glblMsg NMAL7110SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL7110CMsg bizMsg, NMAL7110SMsg glblMsg, String usrId, String glblCmpyCd) {

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

        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNm, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.assnPgmContrFlg, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrCustBidNum, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, pMsg.srchOptTxt_05);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_06.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, pMsg.srchOptTxt_06.getValue());
        } else {
            bizMsg.effFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_07.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt, pMsg.srchOptTxt_07.getValue());
        } else {
            bizMsg.effThruDt.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, pMsg.srchOptTxt_08);

    }
}
