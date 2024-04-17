package business.blap.NFCL3120.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3050.constant.NFCL3050Constant;
import business.blap.NFCL3120.NFCL3120CMsg;
import business.blap.NFCL3120.NFCL3120SMsg;
import business.blap.NFCL3120.constant.NFCL3120Constant;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 *<pre>
 * write-Off Request Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   Fujitsu         T.Tanaka        Create          Initial
 * 2016/07/25   Hitachi         K.Kojima        Update          QC#4870
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#5521
 *</pre>
 */
public class NFCL3120CommonLogic implements NFCL3120Constant {

    // START 2016/07/25 K.Kojima [QC#4870,ADD]
    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFCL3120CMsg cMsg, NFCL3120SMsg sMsg, int pageFrom) {

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
     * @param bizMsg NFCL3120CMsg
     * @param rowNum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> createSsmMap_Identified(NFCL3120CMsg bizMsg, int rowNum) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        String dsBankAcctTpCd = DS_BANK_ACCT_TP.INTERNAL;
        if (bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
            dsBankAcctTpCd = DS_BANK_ACCT_TP.EXTERNAL;
        }

        ssmMap.put("dsBankAcctTpCd", dsBankAcctTpCd);
        ssmMap.put("dsBankAcctNum", bizMsg.dsBankAcctNum_H1.getValue());
        ssmMap.put("dsBankAcctNm", bizMsg.dsBankAcctNm_H1.getValue());
        ssmMap.put("dsBankBrNm", bizMsg.dsBankBrNm_H1.getValue());
        ssmMap.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
        ssmMap.put("dsAcctNm", bizMsg.dsAcctNm_H1.getValue());
        ssmMap.put("procDt", bizMsg.procDt.getValue());
        ssmMap.put("rowNum", rowNum);

        return ssmMap;
    }

    /**
     * @param bizMsg NFCL3120CMsg
     * @param rowNum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> createSsmMap_Unidentified(NFCL3120CMsg bizMsg, int rowNum) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        String dsBankAcctTpCd = DS_BANK_ACCT_TP.INTERNAL;
        if (bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
            dsBankAcctTpCd = DS_BANK_ACCT_TP.EXTERNAL;
        }

        ssmMap.put("dsBankAcctTpCd", dsBankAcctTpCd);
        ssmMap.put("dsBankAcctNum", bizMsg.dsBankAcctNum_H1.getValue());
        ssmMap.put("dsBankAcctNm", bizMsg.dsBankAcctNm_H1.getValue());
        ssmMap.put("dsBankBrNm", bizMsg.dsBankBrNm_H1.getValue());
        ssmMap.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
        ssmMap.put("procDt", bizMsg.procDt.getValue());
        ssmMap.put("rowNum", rowNum);

        return ssmMap;
    }

    // END 2016/07/25 K.Kojima [QC#4870,ADD]

    // START 2016/08/02 K.Kojima [QC#5521,ADD]
    /**
     * clearPageNum
     * @param cMsg NSBL0200CMsg
     */
    public static void clearPageNum(NFCL3120CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NFCL3120CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFCL3120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NFCL3120CMsg
     * @param glblMsg NFCL3120SMsg
     * @param srchOptUsrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NFCL3120CMsg bizMsg, NFCL3120SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFCL3120Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.xxChkBox_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.dsAcctNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.dsBankAcctNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.dsBankBrNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.xxRadioBtn.getValue().toString());

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, glblCmpyCd, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Save Search" });
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NFCL3120CMsg
     * @param glblMsg NFCL3120SMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NFCL3120CMsg bizMsg, NFCL3120SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFCL3050Constant.BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H1, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBankAcctNm_H1, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBankBrNm_H1, pMsg.srchOptTxt_04);
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, new BigDecimal(pMsg.srchOptTxt_05.getValue()));
        } else {
            bizMsg.xxRadioBtn.clear();
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NFCL3120CMsg
     * @param glblMsg NFCL3120SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NFCL3120CMsg bizMsg, NFCL3120SMsg glblMsg, String srchOptUsrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NFCL3120Constant.BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, glblCmpyCd, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    /**
     * callNszc0330
     * @param bizMsg NFCL3120CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NFCL3120CMsg bizMsg, NSZC033001PMsg pMsg) {
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
     * isSameSaveSearchName
     * @param bizMsg NFCL3120CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NFCL3120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NFCL3120CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NFCL3120CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NFBL2060CMsg
     * @param glblCmpyCd String
     * @param userId String
     */
    public static void createSavedSearchOptionsPullDown(NFCL3120CMsg bizMsg, String glblCmpyCd, String userId) {

        // Clear
        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();

        SAVE_SRCH_OPTTMsg sso = new SAVE_SRCH_OPTTMsg();
        sso.setSQLID("001");
        sso.setConditionValue("glblCmpyCd01", glblCmpyCd);
        sso.setConditionValue("srchOptAplId01", BIZ_ID);
        sso.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray ssoList = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(sso);

        for (int i = 0; i < ssoList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H.no(i), ssoList.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_H.no(i), ssoList.no(i).srchOptNm);
        }
    }
    // END 2016/08/02 K.Kojima [QC#5521,ADD]
}
