/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3140.common;

import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_ACCT_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_AFFL_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_BR_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_CC_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_CH_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_CMPY_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_EXTN_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_PROD_CD;
import static business.blap.NFCL3140.constant.NFCL3140Constant.ELIG_COA_SEG_PTRN_COA_PROJ_CD;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3140.NFCL3140CMsg;
import business.blap.NFCL3140.NFCL3140Query;
import business.db.COA_ACCTTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.COA_CMPYTMsg;
import business.db.COA_EXTNTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.DS_INV_GRP_ATTRBTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.ELIG_COA_SEG_PTRNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#7404
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13200
 * 2016/11/30   Fujitsu         T.Murai         Update          QC#15823
 *</pre>
 */
public class NFCL3140CommonLogic {

    /**
     * getPulldownListInvTp
     * @param cMsg NFCL3140CMsg
     */
    public static void getPulldownListInvTp(NFCL3140CMsg cMsg) {
        S21SsmEZDResult ssmResult = NFCL3140Query.getInstance().getPulldownListInvTp(cMsg.glblCmpyCd.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < result.size(); i++) {
                Map<String, Object> map = result.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.invTpCd_CD.no(i), (String) map.get("INV_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.invTpDescTxt_SC.no(i), (String) map.get("INV_TP_DESC_TXT"));
            }
        }
    }

    /**
     * getPulldownListGrouping
     * @param cMsg NFCL3140CMsg
     */
    public static void getPulldownListGrouping(NFCL3140CMsg cMsg) {
        NFCL3140Query.getInstance().getPulldownListGrouping(cMsg.B, cMsg.glblCmpyCd.getValue());
        getPulldownListGroupingNoAccess(cMsg);
    }

    /**
     * getPulldownListGrouping
     * @param cMsg NFCL3140CMsg
     */
    public static void getPulldownListGroupingNoAccess(NFCL3140CMsg cMsg) {
        cMsg.A.setValidCount(0);
        // START 2016/05/23 T.Tsuchida [QC#7404,MOD]
        //for (int i = 0; i < PULLDOWN_SIZE; i++) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
        // END 2016/05/23 T.Tsuchida [QC#7404,MOD]
            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).invGrpAttrbTxt_CD.no(j), cMsg.B.no(j).invGrpAttrbTxt);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).invGrpAttrbTxt_SC.no(j), cMsg.B.no(j).invGrpAttrbTxt);
            }
            cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
        }
    }

    // START 2016/08/09 K.Kojima [QC#13200,ADD]
    /**
     * getPulldownListAutoSequence
     * @param cMsg NFCL3140CMsg
     */
    public static void getPulldownListAutoSequence(NFCL3140CMsg cMsg) {
        String dsInvTpAutoSeqCd = ZYPCodeDataUtil.getVarCharConstValue("DS_INV_TP_AUTO_SEQ_CD", cMsg.glblCmpyCd.getValue());
        String[] strs = new String[0];
        if (ZYPCommonFunc.hasValue(dsInvTpAutoSeqCd)) {
            strs = dsInvTpAutoSeqCd.split(",");
        }
        for (int i = 0; i < strs.length; i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.autoSeqCd_CD.no(i), strs[i]);
            ZYPEZDItemValueSetter.setValue(cMsg.autoSeqCd_SC.no(i), strs[i]);
        }
    }

    // END 2016/08/09 K.Kojima [QC#13200,ADD]

    /**
     * getDetailData
     * @param cMsg NFCL3140CMsg
     */
    public static void getDetailData(NFCL3140CMsg cMsg) {
        NFCL3140Query.getInstance().getDetailData(cMsg, cMsg.glblCmpyCd.getValue(), cMsg.dsInvTpCd.getValue());
    }

    /**
     * getGroupingData
     * @param cMsg NFCL3140CMsg
     */
    public static void getGroupingData(NFCL3140CMsg cMsg) {
        NFCL3140Query.getInstance().getGroupingData(cMsg.A, cMsg.glblCmpyCd.getValue(), cMsg.dsInvTpCd.getValue());
    }

    /**
     * checkSameNmData
     * @param cMsg NFCL3140CMsg
     * @return boolean
     */
    public static boolean checkSameNmData(NFCL3140CMsg cMsg) {
        String result = NFCL3140Query.getInstance().getSameNmData(cMsg.glblCmpyCd.getValue(), cMsg.dsInvTpNm.getValue(), cMsg.dsInvTpCd.getValue());
        if (result == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getMaxData
     * @param cMsg NFCL3140CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getMaxData(NFCL3140CMsg cMsg) {
        Map<String, Object> result = NFCL3140Query.getInstance().getMaxData(cMsg.glblCmpyCd.getValue());
        return result;
    }

    /**
     * getBeforeData
     * @param cMsg NFCL3140CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getBeforeData(NFCL3140CMsg cMsg) {
        Map<String, Object> result = NFCL3140Query.getInstance().getBeforeData(cMsg.glblCmpyCd.getValue(), cMsg.dsInvTpCd.getValue());
        return result;
    }

    /**
     * checkCrDsInvTpCd
     * @param cMsg NFCL3140CMsg
     * @return boolean
     */
    public static boolean checkCrDsInvTpCd(NFCL3140CMsg cMsg) {
        String result = NFCL3140Query.getInstance().getCrDsInvTpCd(cMsg.glblCmpyCd.getValue(), cMsg.dsInvTpCd.getValue());
        if (result == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checkGroupingAttributed
     * @param cMsg NFCL3140CMsg
     * @return boolean
     */
    public static boolean checkGroupingAttributed(NFCL3140CMsg cMsg, String messageCode, String[] messageArgs) {
        HashMap<String, Integer> invGrpAtrbTxtMap = new HashMap<String, Integer>();
        boolean checkResult = true;
        for (int i = 0; i < cMsg.A.length(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).invGrpAttrbTxt_SV)) {
                if (invGrpAtrbTxtMap.containsKey(cMsg.A.no(i).invGrpAttrbTxt_SV.getValue())) {
                    Integer target = invGrpAtrbTxtMap.get(cMsg.A.no(i).invGrpAttrbTxt_SV.getValue());
                    cMsg.A.no(target.intValue()).invGrpAttrbTxt_SV.setErrorInfo(1, messageCode, messageArgs);
                    cMsg.A.no(i).invGrpAttrbTxt_SV.setErrorInfo(1, messageCode, messageArgs);
                    checkResult = false;
                }
                invGrpAtrbTxtMap.put(cMsg.A.no(i).invGrpAttrbTxt_SV.getValue(), new Integer(i));
            }
        }
        return checkResult;
    }

    /**
     * checkCoaCmpyAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaCmpyCd String
     * @return boolean
     */
    public static boolean checkCoaCmpyAndEligCoaSegPtrn(String glblCmpyCd, String coaCmpyCd) {
        if (checkCoaCmpy(glblCmpyCd, coaCmpyCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_CMPY_CD, coaCmpyCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaBrAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return boolean
     */
    public static boolean checkCoaBrAndEligCoaSegPtrn(String glblCmpyCd, String coaBrCd) {
        if (checkCoaBr(glblCmpyCd, coaBrCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_BR_CD, coaBrCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaCcCdAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaCcCd String
     * @return boolean
     */
    public static boolean checkCoaCcCdAndEligCoaSegPtrn(String glblCmpyCd, String coaCcCd) {
        if (checkCoaCc(glblCmpyCd, coaCcCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_CC_CD, coaCcCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaAcctAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @return boolean
     */
    public static boolean checkCoaAcctAndEligCoaSegPtrn(String glblCmpyCd, String coaAcctCd) {
        if (checkCoaAcct(glblCmpyCd, coaAcctCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_ACCT_CD, coaAcctCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaProdAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaProdCd String
     * @return boolean
     */
    public static boolean checkCoaProdAndEligCoaSegPtrn(String glblCmpyCd, String coaProdCd) {
        if (checkCoaProd(glblCmpyCd, coaProdCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_PROD_CD, coaProdCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaChAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaChCd String
     * @return boolean
     */
    public static boolean checkCoaChAndEligCoaSegPtrn(String glblCmpyCd, String coaChCd) {
        if (checkCoaCh(glblCmpyCd, coaChCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_CH_CD, coaChCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaAfflAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaAfflCd String
     * @return boolean
     */
    public static boolean checkCoaAfflAndEligCoaSegPtrn(String glblCmpyCd, String coaAfflCd) {
        if (checkCoaAffl(glblCmpyCd, coaAfflCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_AFFL_CD, coaAfflCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaProjAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaProjCd String
     * @return boolean
     */
    public static boolean checkCoaProjAndEligCoaSegPtrn(String glblCmpyCd, String coaProjCd) {
        if (checkCoaProj(glblCmpyCd, coaProjCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_PROJ_CD, coaProjCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaExtnAndEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param coaExtnCd String
     * @return boolean
     */
    public static boolean checkCoaExtnAndEligCoaSegPtrn(String glblCmpyCd, String coaExtnCd) {
        if (checkCoaExtn(glblCmpyCd, coaExtnCd)) {
            return true;
        } else if (checkEligCoaSegPtrn(glblCmpyCd, ELIG_COA_SEG_PTRN_COA_EXTN_CD, coaExtnCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkCoaCmpy
     * @param glblCmpyCd String
     * @param coaCmpyCd String
     * @return boolean
     */
    private static boolean checkCoaCmpy(String glblCmpyCd, String coaCmpyCd) {
        if (coaCmpyCd == null || coaCmpyCd.length() > 3) {
            return false;
        }
        COA_CMPYTMsg inMsg = new COA_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCmpyCd, coaCmpyCd);
        COA_CMPYTMsg outMsg = (COA_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaBr
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return boolean
     */
    private static boolean checkCoaBr(String glblCmpyCd, String coaBrCd) {
        if (coaBrCd == null || coaBrCd.length() > 3) {
            return false;
        }
        COA_BRTMsg inMsg = new COA_BRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaBrCd, coaBrCd);
        COA_BRTMsg outMsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaCc
     * @param glblCmpyCd String
     * @param coaCcCd String
     * @return boolean
     */
    private static boolean checkCoaCc(String glblCmpyCd, String coaCcCd) {
        if (coaCcCd == null || coaCcCd.length() > 6) {
            return false;
        }
        COA_CCTMsg inMsg = new COA_CCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaCcCd, coaCcCd);
        COA_CCTMsg outMsg = (COA_CCTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaAcct
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @return boolean
     */
    private static boolean checkCoaAcct(String glblCmpyCd, String coaAcctCd) {
        if (coaAcctCd == null || coaAcctCd.length() > 8) {
            return false;
        }
        COA_ACCTTMsg inMsg = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAcctCd, coaAcctCd);
        COA_ACCTTMsg outMsg = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaProd
     * @param glblCmpyCd String
     * @param coaProdCd String
     * @return boolean
     */
    private static boolean checkCoaProd(String glblCmpyCd, String coaProdCd) {
        if (coaProdCd == null || coaProdCd.length() > 8) {
            return false;
        }
        COA_PRODTMsg inMsg = new COA_PRODTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, coaProdCd);
        COA_PRODTMsg outMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaCh
     * @param glblCmpyCd String
     * @param coaChCd String
     * @return boolean
     */
    private static boolean checkCoaCh(String glblCmpyCd, String coaChCd) {
        if (coaChCd == null || coaChCd.length() > 3) {
            return false;
        }
        COA_CHTMsg inMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaChCd, coaChCd);
        COA_CHTMsg outMsg = (COA_CHTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaAffl
     * @param glblCmpyCd String
     * @param coaAfflCd String
     * @return boolean
     */
    private static boolean checkCoaAffl(String glblCmpyCd, String coaAfflCd) {
        if (coaAfflCd == null || coaAfflCd.length() > 3) {
            return false;
        }
        COA_AFFLTMsg inMsg = new COA_AFFLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaAfflCd, coaAfflCd);
        COA_AFFLTMsg outMsg = (COA_AFFLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaProj
     * @param glblCmpyCd String
     * @param coaProjCd String
     * @return boolean
     */
    private static boolean checkCoaProj(String glblCmpyCd, String coaProjCd) {
        if (coaProjCd == null || coaProjCd.length() > 4) {
            return false;
        }
        COA_PROJTMsg inMsg = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProjCd, coaProjCd);
        COA_PROJTMsg outMsg = (COA_PROJTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkCoaExtn
     * @param glblCmpyCd String
     * @param coaExtnCd String
     * @return boolean
     */
    private static boolean checkCoaExtn(String glblCmpyCd, String coaExtnCd) {
        if (coaExtnCd == null || coaExtnCd.length() > 3) {
            return false;
        }
        COA_EXTNTMsg inMsg = new COA_EXTNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaExtnCd, coaExtnCd);
        COA_EXTNTMsg outMsg = (COA_EXTNTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkEligCoaSegPtrn
     * @param glblCmpyCd String
     * @param eligCoaSegPtrnCd String
     * @param coaSegLkupTpCd String
     * @return boolean
     */
    private static boolean checkEligCoaSegPtrn(String glblCmpyCd, String eligCoaSegPtrnCd, String coaSegLkupTpCd) {
        ELIG_COA_SEG_PTRNTMsg inMsg = new ELIG_COA_SEG_PTRNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.eligCoaSegPtrnCd, eligCoaSegPtrnCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaSegLkupTpCd, coaSegLkupTpCd);
        ELIG_COA_SEG_PTRNTMsg outMsg = (ELIG_COA_SEG_PTRNTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * insertDsInvTp
     * @param cMsg NFCL3140CMsg
     * @param dsInvTpCd String
     * @param dsInvTpSortNum BigDecimal
     */
    public static boolean insertDsInvTp(NFCL3140CMsg cMsg, String dsInvTpCd, BigDecimal dsInvTpSortNum) {
        DS_INV_TPTMsg tMsg = new DS_INV_TPTMsg();
        setParam(cMsg, tMsg, dsInvTpCd, dsInvTpSortNum);
        EZDTBLAccessor.create(tMsg);
        // START 2016/11/30 [QC#15823, ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo("NFCM0616E", new String[] {cMsg.dsInvTpNm.getValue() , "DS_INV_TP"});
            return false;
        }
        // END 2016/11/30 [QC#15823, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            EZDTBLAccessor.logicalRemove(tMsg);
        }
        return true; // ADD 2016/11/30 [QC#15823]
    }

    /**
     * updaetDsInvTp
     * @param cMsg NFCL3140CMsg
     * @param dsInvTpSortNum BigDecimal
     */
    public static boolean updaetDsInvTp(NFCL3140CMsg cMsg, BigDecimal dsInvTpSortNum) {
        DS_INV_TPTMsg tMsg = new DS_INV_TPTMsg();
        setParam(cMsg, tMsg, cMsg.dsInvTpCd.getValue(), dsInvTpSortNum);
        EZDTBLAccessor.update(tMsg);
        // START 2016/11/30 [QC#15823, ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo("NFCM0615E", new String[] {"DS_INV_TP"});
            return false;
        }
        // END 2016/11/30 [QC#15823, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            EZDTBLAccessor.logicalRemove(tMsg);
        }
        return true; // ADD 2016/11/30 [QC#15823]
    }

    /**
     * setParam
     * @param cMsg NFCL3140CMsg
     * @param tMsg DS_INV_TPTMsg
     * @param dsInvTpCd String
     * @param dsInvTpSortNum BigDecimal
     */
    private static void setParam(NFCL3140CMsg cMsg, DS_INV_TPTMsg tMsg, String dsInvTpCd, BigDecimal dsInvTpSortNum) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_ON_1);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpCd, dsInvTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpNm, cMsg.dsInvTpNm);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpDescTxt, cMsg.dsInvTpDescTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpSortNum, dsInvTpSortNum);
        ZYPEZDItemValueSetter.setValue(tMsg.invTpCd, cMsg.invTpCd_SV);
        ZYPEZDItemValueSetter.setValue(tMsg.crDsInvTpCd, cMsg.dsInvTpCd_AC);
        ZYPEZDItemValueSetter.setValue(tMsg.invSrcTxt, cMsg.invSrcTxt);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.postToGlFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.postToGlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.postToGlFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.openArFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.openArFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.openArFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.taxCalcFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.taxExemFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.taxExemFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.taxExemFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.taxExemRsnCd, (String) null);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.invPrintFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.invPrintFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.invPrintFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.autoInvNumFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.autoInvNumFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.autoInvNumFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaCmpyCd, cMsg.arCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaBrCd, cMsg.arCoaBrCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaCcCd, cMsg.arCoaCcCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaAcctCd, cMsg.arCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaProdCd, cMsg.arCoaProdCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaChCd, cMsg.arCoaChCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaAfflCd, cMsg.arCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaProjCd, cMsg.arCoaProjCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arCoaExtnCd, cMsg.arCoaExtnCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaCmpyCd, cMsg.slsCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaBrCd, cMsg.slsCoaBrCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaCcCd, cMsg.slsCoaCcCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaAcctCd, cMsg.slsCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaProdCd, cMsg.slsCoaProdCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaChCd, cMsg.slsCoaChCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaAfflCd, cMsg.slsCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaProjCd, cMsg.slsCoaProjCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCoaExtnCd, cMsg.slsCoaExtnCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaCmpyCd, cMsg.taxCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaBrCd, cMsg.taxCoaBrCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaCcCd, cMsg.taxCoaCcCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaAcctCd, cMsg.taxCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaProdCd, cMsg.taxCoaProdCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaChCd, cMsg.taxCoaChCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaAfflCd, cMsg.taxCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaProjCd, cMsg.taxCoaProjCd);
        ZYPEZDItemValueSetter.setValue(tMsg.taxCoaExtnCd, cMsg.taxCoaExtnCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaCmpyCd, cMsg.unEarnCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaBrCd, cMsg.unEarnCoaBrCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaCcCd, cMsg.unEarnCoaCcCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaAcctCd, cMsg.unEarnCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaProdCd, cMsg.unEarnCoaProdCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaChCd, cMsg.unEarnCoaChCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaAfflCd, cMsg.unEarnCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaProjCd, cMsg.unEarnCoaProjCd);
        ZYPEZDItemValueSetter.setValue(tMsg.unEarnCoaExtnCd, cMsg.unEarnCoaExtnCd);
        // START 2016/08/09 K.Kojima [QC#13200,MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.autoSeqCd, (String)
        // null);
        ZYPEZDItemValueSetter.setValue(tMsg.autoSeqCd, cMsg.autoSeqCd_SV);
        // END 2016/08/09 K.Kojima [QC#13200,MOD]
    }

    /**
     * deleteDsInvGrpAttrb
     * @param cMsg NFCL3140CMsg
     */
    public static void deleteDsInvGrpAttrb(NFCL3140CMsg cMsg) {
        List<BigDecimal> result = NFCL3140Query.getInstance().getDeleteTarget(cMsg.glblCmpyCd.getValue(), cMsg.dsInvTpCd.getValue());
        if (result != null) {
            for (BigDecimal invGrpAttrbSqNum : result) {
                DS_INV_GRP_ATTRBTMsg tMsg = new DS_INV_GRP_ATTRBTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpCd, cMsg.dsInvTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.invGrpAttrbSqNum, invGrpAttrbSqNum);
                EZDTBLAccessor.logicalRemove(tMsg);
            }
        }
    }

    /**
     * insertDsInvGrpAttrb
     * @param cMsg NFCL3140CMsg
     * @param dsInvTpCd String
     */
    public static boolean insertDsInvGrpAttrb(NFCL3140CMsg cMsg, String dsInvTpCd) {
        BigDecimal invGrpAttrbSqNum = BigDecimal.ONE;
        for (int i = 0; i < cMsg.A.length(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).invGrpAttrbTxt_SV)) {
                DS_INV_GRP_ATTRBTMsg inMsg = new DS_INV_GRP_ATTRBTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(inMsg.dsInvTpCd, dsInvTpCd);
                ZYPEZDItemValueSetter.setValue(inMsg.invGrpAttrbSqNum, invGrpAttrbSqNum);
                ZYPEZDItemValueSetter.setValue(inMsg.invGrpAttrbTxt, cMsg.A.no(i).invGrpAttrbTxt_SV);
                EZDTBLAccessor.create(inMsg);
                invGrpAttrbSqNum = invGrpAttrbSqNum.add(BigDecimal.ONE);

                // START 2016/11/30 [QC#15823, ADD]
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    cMsg.setMessageInfo("NFCM0616E", new String[] {cMsg.dsInvTpNm.getValue(), "DS_INV_GRP_ATTRB"});
                    return false;
                }
                // END 2016/11/30 [QC#15823, ADD]
            }
        }
        return true;
    }

    // START 2016/08/09 K.Kojima [QC#13200,ADD]
    /**
     * getExtCurSqNum
     * @param cMsg NFCL3140CMsg
     */
    public static void getExtCurSqNum(NFCL3140CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.autoSeqCd_SV)) {
            ZYPEZDItemValueSetter.setValue(cMsg.extCurSqNum, NFCL3140Query.getInstance().getExtCurSqNum(cMsg.glblCmpyCd.getValue(), cMsg.autoSeqCd_SV.getValue()));
        } else {
            cMsg.extCurSqNum.clear();
        }
    }
    // END 2016/08/09 K.Kojima [QC#13200,ADD]
}
