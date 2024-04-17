/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0050.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0050.NFAL0050CMsg;
import business.blap.NFAL0050.NFAL0050Query;
import business.blap.NFAL0050.constant.NFAL0050Constant;
import business.db.AJE_LINE_IND_TPTMsg;
import business.db.AJE_LINE_IND_TPTMsgArray;
import business.db.AJE_PTRNTMsg;
import business.db.AJE_PTRNTMsgArray;
import business.db.COA_BRTMsg;
import business.db.COA_BRTMsgArray;
import business.db.COA_CHTMsg;
import business.db.COA_CHTMsgArray;
import business.db.COA_CMPYTMsg;
import business.db.COA_CMPYTMsgArray;
import business.db.COA_EXTNTMsg;
import business.db.COA_EXTNTMsgArray;
import business.db.ELIG_COA_SEG_PTRNTMsg;
import business.db.ELIG_COA_SEG_PTRNTMsgArray;
import business.db.JRNL_CATGTMsg;
import business.db.JRNL_CATGTMsgArray;
import business.db.SYS_SRCTMsg;
import business.db.SYS_SRCTMsgArray;
import business.db.TRXTMsg;
import business.db.TRXTMsgArray;
import business.db.TRX_RSNTMsg;
import business.db.TRX_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NFAL0050CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050CommonLogic implements NFAL0050Constant {

    /** S21UserProfileService profileService */
    private S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** String globalCompanyCode */
    private String globalCompanyCode = profileService.getGlobalCompanyCode();

    /**
     * Method name: getGlobalCompanyCode
     * <dd>The method explanation:
     * <dd>Remarks:
     * @return String globalCompanyCode
     */
    public String getGlobalCompanyCode() {
        return globalCompanyCode;
    }

    /**
     * Method name: setSearchedResult
     * <dd>The method explanation: Get Month List.
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @param reset boolean
     */
    public void setSearchedResult(NFAL0050CMsg bizMsg, boolean reset) {

        clearFields(bizMsg);

        if (reset && (bizMsg.ajeId.isClear() || !allIndTpNotBlank(bizMsg))) {
            bizMsg.xxSetFlg.setValue(HAS_NO_RESULT);
            return;
        }

        // <ID>804</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // AJE_ID = ?ajeId01? AND
        // AJE_PTRN_IND_TP_CD_01 = ?ajePtrnIndTpCd0101? AND
        // AJE_PTRN_ACTL_CD_01 = ?ajePtrnActlCd0101? AND
        // AJE_PTRN_IND_TP_CD_02 = ?ajePtrnIndTpCd0201? AND
        // AJE_PTRN_ACTL_CD_02 = ?ajePtrnActlCd0201? AND
        // AJE_PTRN_IND_TP_CD_03 = ?ajePtrnIndTpCd0301? AND
        // AJE_PTRN_ACTL_CD_03 = ?ajePtrnActlCd0301?
        // ORDER BY
        // AJE_ID ASC,
        // AJE_PTRN_IND_TP_CD_01 ASC,
        // AJE_LINE_IDX_NUM ASC,
        // DR_CR_TP_CD DESC
        AJE_PTRNTMsg tMsg = new AJE_PTRNTMsg();
        tMsg.setSQLID("804");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("ajeId01", bizMsg.ajeId.getValue());
        tMsg.setConditionValue("ajePtrnIndTpCd0101", bizMsg.ajePtrnIndTpCd_1V.getValue());
        tMsg.setConditionValue("ajePtrnActlCd0101", bizMsg.ajePtrnActlCd_1V.getValue());
        tMsg.setConditionValue("ajePtrnIndTpCd0201", bizMsg.ajePtrnIndTpCd_2V.getValue());
        tMsg.setConditionValue("ajePtrnActlCd0201", bizMsg.ajePtrnActlCd_2V.getValue());
        tMsg.setConditionValue("ajePtrnIndTpCd0301", bizMsg.ajePtrnIndTpCd_3V.getValue());
        tMsg.setConditionValue("ajePtrnActlCd0301", bizMsg.ajePtrnActlCd_3V.getValue());

        AJE_PTRNTMsgArray tMsgArr = (AJE_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            // clearFileds(bizMsg);
            int i = 0;
            for (; i < tMsgArr.length(); i++) {
                if (i == 0) {
                    // Set searched Time & Time zone
                    bizMsg.ezUpTime.setValue(tMsgArr.no(0).ezUpTime.getValue());
                    bizMsg.ezUpTimeZone.setValue(tMsgArr.no(0).ezUpTimeZone.getValue());

                    bizMsg.jrnlCatgCd.setValue(tMsgArr.no(0).jrnlCatgCd.getValue());
                    bizMsg.jrnlCatgNm.setValue(getJrnlCatgNm(tMsgArr.no(0).jrnlCatgCd.getValue()));
                }
                bizMsg.A.no(i).ajeLineIdxNum_A.setValue(tMsgArr.no(i).ajeLineIdxNum.getValue());
                bizMsg.A.no(i).drCrTpCd_A.setValue(tMsgArr.no(i).drCrTpCd.getValue());
                bizMsg.A.no(i).ajeCoaCmpyCd_A.setValue(tMsgArr.no(i).ajeCoaCmpyCd.getValue());
                bizMsg.A.no(i).ajeCoaBrCd_A.setValue(tMsgArr.no(i).ajeCoaBrCd.getValue());
                bizMsg.A.no(i).ajeCoaCcCd_A.setValue(tMsgArr.no(i).ajeCoaCcCd.getValue());
                bizMsg.A.no(i).ajeCoaAcctCd_A.setValue(tMsgArr.no(i).ajeCoaAcctCd.getValue());

                bizMsg.A.no(i).ajeLineIndTpCd_A.setValue(tMsgArr.no(i).ajeLineIndTpCd.getValue());
                bizMsg.A.no(i).ajeLineIndTpNm_A.setValue(getLineIndTpNm(bizMsg, tMsgArr.no(i).ajeLineIndTpCd.getValue()));
                bizMsg.A.no(i).ajeLineIdxDescTxt_A.setValue(tMsgArr.no(i).ajeLineIdxDescTxt.getValue());

                bizMsg.A.no(i).ajeCoaProdCd_A.setValue(tMsgArr.no(i).ajeCoaProdCd.getValue());
                bizMsg.A.no(i).ajeCoaChCd_A.setValue(tMsgArr.no(i).ajeCoaChCd.getValue());
                bizMsg.A.no(i).ajeCoaAfflCd_A.setValue(tMsgArr.no(i).ajeCoaAfflCd.getValue());
                bizMsg.A.no(i).ajeCoaProjCd_A.setValue(tMsgArr.no(i).ajeCoaProjCd.getValue());
                bizMsg.A.no(i).ajeCoaExtnCd_A.setValue(tMsgArr.no(i).ajeCoaExtnCd.getValue());
            } // for
            bizMsg.A.setValidCount(i);

            // START QC#19964
            bizMsg.xxChkBox_AM.setValue(tMsgArr.no(0).jrnlAmtFlipFlg.getValue());
            bizMsg.xxChkBox_QT.setValue(tMsgArr.no(0).jrnlQtyFlipFlg.getValue());
            // END  QC#19964

            bizMsg.xxSetFlg.setValue(HAS_RESULT);
        } else {
            bizMsg.ezUpTime.clear();
            bizMsg.ezUpTimeZone.clear();

            // Need to be done before set default val
            bizMsg.setMessageInfo("NFAM0042W", new String[] {AJE_ID });
            bizMsg.xxSetFlg.setValue(HAS_NO_RESULT);
        }
    }

    /**
     * Method name: getNumberOfAjePtrn
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @return int resultCnt
     */
    public int getNumberOfAjePtrn(NFAL0050CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getAjeIdsGroupByIndTp(bizMsg);
        clearFields(bizMsg);

        int resultCnt = 0;
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            resultCnt = ssmResult.getQueryResultCount();
            if (resultCnt == 1) {
                // If it's paste mode, not to set automatically
                if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                    Map map = (Map) resultList.get(0);
                    bizMsg.ajePtrnIndTpCd_1V.setValue((String) map.get("AJE_PTRN_IND_TP_CD_01"));
                    bizMsg.ajePtrnActlCd_1V.setValue((String) map.get("AJE_PTRN_ACTL_CD_01"));
                    bizMsg.ajePtrnIndTpCd_2V.setValue((String) map.get("AJE_PTRN_IND_TP_CD_02"));
                    bizMsg.ajePtrnActlCd_2V.setValue((String) map.get("AJE_PTRN_ACTL_CD_02"));
                    bizMsg.ajePtrnIndTpCd_3V.setValue((String) map.get("AJE_PTRN_IND_TP_CD_03"));
                    bizMsg.ajePtrnActlCd_3V.setValue((String) map.get("AJE_PTRN_ACTL_CD_03"));
                }
            }
        } else {
            bizMsg.ezUpTime.clear();
            bizMsg.ezUpTimeZone.clear();
        }
        bizMsg.xxTotCnt_P.setValue(new BigDecimal(resultCnt));
        return resultCnt;
    }

    private String getJrnlCatgNm(String jrnlCatgCd) {

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // JRNL_CATG_CD = ?jrnlCatgCd01?
        JRNL_CATGTMsg tMsg = new JRNL_CATGTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        tMsg.setConditionValue("jrnlCatgCd01", jrnlCatgCd);
        JRNL_CATGTMsgArray tMsgArr = (JRNL_CATGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            return tMsgArr.no(0).jrnlCatgNm.getValue();
        } else {
            return BLANK;
        }
    }

    /**
     * Method name: clearFields
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     */
    public void clearFields(NFAL0050CMsg bizMsg) {
        // Journal Category
        bizMsg.jrnlCatgCd.clear();
        bizMsg.jrnlCatgNm.clear();
        // Add Row fields
        clearAddRow(bizMsg);
        // List
        if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            clearList(bizMsg);
        }
    }

    private void clearAddRow(NFAL0050CMsg bizMsg) {
        bizMsg.ajeLineIdxNum_3.clear();
        bizMsg.ajeCoaCmpyCd_3.clear();
        
        //---- start mod 2016/01/21 
        // bizMsg.ajeCoaBrCd_3.clear();
        //---- end

        bizMsg.ajeCoaCcCd.clear();
        bizMsg.ajeCoaAcctCd.clear();
        bizMsg.ajeLineIndTpCd_3.clear();

        bizMsg.ajeLineIdxDescTxt.clear();
        bizMsg.ajeCoaProdCd.clear();

        bizMsg.ajeCoaChCd_3.clear();
        bizMsg.ajeCoaAfflCd.clear();
        bizMsg.ajeCoaProjCd.clear();
        bizMsg.ajeCoaExtnCd_3.clear();
    }

    private void clearList(NFAL0050CMsg bizMsg) {
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // bizMsg.C.clear();
        // bizMsg.C.setValidCount(0);
    }

    /**
     * Method name: initListBoxes
     * <dd>The method explanation: Get Month List.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public void initListBoxes(EZDCMsg cMsg) {
        // Sys Src
        setSysSrcListBox(cMsg);
        // Trx
        setTrxListBox(cMsg);
        // List boxes for Add Row
        setLineIdxListBox(cMsg);
        setCmpyCdListBox(cMsg);
        //---- start mod 2016/01/21
        //setBrCdListBox(cMsg);
        //---- end
        setAjeLineIndTpListBox(cMsg);
        setChCdListBox(cMsg);
        setExtnCdListBox(cMsg);
        // AJE Pattern Ind Type
        setIndTpNm(cMsg);
    }

    private void setIndTpNm(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajePtrnIndTpCd_1C.clear();
        bizMsg.ajePtrnIndTpNm_1D.clear();
        bizMsg.ajePtrnIndTpCd_1V.clear();

        bizMsg.ajePtrnIndTpCd_2C.clear();
        bizMsg.ajePtrnIndTpNm_2D.clear();
        bizMsg.ajePtrnIndTpCd_2V.clear();

        bizMsg.ajePtrnIndTpCd_3C.clear();
        bizMsg.ajePtrnIndTpNm_3D.clear();
        bizMsg.ajePtrnIndTpCd_3V.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getAllIndTpCd(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnIndTpCd_1C.no(i).setValue((String) map.get("AJE_PTRN_IND_TP_CD"));
                bizMsg.ajePtrnIndTpNm_1D.no(i).setValue((String) map.get("AJE_PTRN_IND_TP_NM"));

                bizMsg.ajePtrnIndTpCd_2C.no(i).setValue((String) map.get("AJE_PTRN_IND_TP_CD"));
                bizMsg.ajePtrnIndTpNm_2D.no(i).setValue((String) map.get("AJE_PTRN_IND_TP_NM"));

                bizMsg.ajePtrnIndTpCd_3C.no(i).setValue((String) map.get("AJE_PTRN_IND_TP_CD"));
                bizMsg.ajePtrnIndTpNm_3D.no(i).setValue((String) map.get("AJE_PTRN_IND_TP_NM"));
            }
        }
    }

    private void setSysSrcListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.sysSrcCd_1.clear();
        bizMsg.sysSrcCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // SYS_SRC_SORT_NUM ASC
        SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        SYS_SRCTMsgArray tMsgArr = (SYS_SRCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.sysSrcCd_1.no(i).setValue(tMsgArr.no(i).sysSrcCd.getValue());
                bizMsg.sysSrcCd_2.no(i).setValue(tMsgArr.no(i).sysSrcCd.getValue());
            }
        }
    }

    private void setTrxListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.trxCd_1.clear();
        bizMsg.trxCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // TRX_SORT_NUM ASC
        TRXTMsg tMsg = new TRXTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        TRXTMsgArray tMsgArr = (TRXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxCd_1.no(i).setValue(tMsgArr.no(i).trxCd.getValue());
                bizMsg.trxCd_2.no(i).setValue(tMsgArr.no(i).trxCd.getValue());
            }
        }
    }

    private void setLineIdxListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajeLineIdxNum_1.clear();
        bizMsg.ajeLineIdxNum_2.clear();

        for (int i = 0; i < LINE_IDX_NUM_ARR.length; i++) {
            bizMsg.ajeLineIdxNum_1.no(i).setValue(LINE_IDX_NUM_ARR[i]);
            bizMsg.ajeLineIdxNum_2.no(i).setValue(LINE_IDX_NUM_ARR[i]);
        }
    }

    private void setCmpyCdListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajeCoaCmpyCd_1.clear();
        bizMsg.ajeCoaCmpyCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // COA_CMPY_CD ASC
        COA_CMPYTMsg tMsgCoaCmpy = new COA_CMPYTMsg();
        tMsgCoaCmpy.setSQLID("801");
        tMsgCoaCmpy.setConditionValue("glblCmpyCd01", globalCompanyCode);
        COA_CMPYTMsgArray tMsgCoaCmpyArr = (COA_CMPYTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaCmpy);

        if (tMsgCoaCmpyArr != null && tMsgCoaCmpyArr.length() > 0) {
            for (int i = 0; i < tMsgCoaCmpyArr.length(); i++) {
                bizMsg.ajeCoaCmpyCd_1.no(i).setValue(tMsgCoaCmpyArr.no(i).coaCmpyCd.getValue());
                bizMsg.ajeCoaCmpyCd_2.no(i).setValue(tMsgCoaCmpyArr.no(i).coaCmpyCd.getValue());
            }
            // <ID>801</ID>
            // <query><![CDATA[
            // WHERE
            // GLBL_CMPY_CD = ?glblCmpyCd01? AND
            // EZCANCELFLAG = '0' AND
            // ELIG_COA_SEG_PTRN_CD = ?eligCoaSegPtrnCd01?
            ELIG_COA_SEG_PTRNTMsg tMsgEligCoaSegPtrn = new ELIG_COA_SEG_PTRNTMsg();
            tMsgEligCoaSegPtrn.setSQLID("801");
            tMsgEligCoaSegPtrn.setConditionValue("glblCmpyCd01", globalCompanyCode);
            tMsgEligCoaSegPtrn.setConditionValue("eligCoaSegPtrnCd01", COA_CMPY_CD);
            ELIG_COA_SEG_PTRNTMsgArray tMsgEligCoaSegPtrnArr = (ELIG_COA_SEG_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsgEligCoaSegPtrn);

            if (tMsgEligCoaSegPtrnArr != null && tMsgEligCoaSegPtrnArr.length() > 0) {
                int j = 0;
                for (int i = tMsgCoaCmpyArr.length(); i < (tMsgCoaCmpyArr.length() + tMsgEligCoaSegPtrnArr.length()); i++) {
                    bizMsg.ajeCoaCmpyCd_1.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    bizMsg.ajeCoaCmpyCd_2.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    j++;
                }
            }
        }
    }

    /*
    private void setBrCdListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajeCoaBrCd_1.clear();
        bizMsg.ajeCoaBrCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // COA_BR_CD ASC
        COA_BRTMsg tMsgCoaBr = new COA_BRTMsg();
        tMsgCoaBr.setSQLID("801");
        tMsgCoaBr.setConditionValue("glblCmpyCd01", globalCompanyCode);
        COA_BRTMsgArray tMsgCoaBrArr = (COA_BRTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaBr);

        if (tMsgCoaBrArr != null && tMsgCoaBrArr.length() > 0) {
            for (int i = 0; i < tMsgCoaBrArr.length(); i++) {
                bizMsg.ajeCoaBrCd_1.no(i).setValue(tMsgCoaBrArr.no(i).coaBrCd.getValue());
                bizMsg.ajeCoaBrCd_2.no(i).setValue(tMsgCoaBrArr.no(i).coaBrCd.getValue());
            }
            // <ID>801</ID>
            // <query><![CDATA[
            // WHERE
            // GLBL_CMPY_CD = ?glblCmpyCd01? AND
            // EZCANCELFLAG = '0' AND
            // ELIG_COA_SEG_PTRN_CD = ?eligCoaSegPtrnCd01?
            ELIG_COA_SEG_PTRNTMsg tMsgEligCoaSegPtrn = new ELIG_COA_SEG_PTRNTMsg();
            tMsgEligCoaSegPtrn.setSQLID("801");
            tMsgEligCoaSegPtrn.setConditionValue("glblCmpyCd01", globalCompanyCode);
            tMsgEligCoaSegPtrn.setConditionValue("eligCoaSegPtrnCd01", COA_BR_CD);
            ELIG_COA_SEG_PTRNTMsgArray tMsgEligCoaSegPtrnArr = (ELIG_COA_SEG_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsgEligCoaSegPtrn);

            if (tMsgEligCoaSegPtrnArr != null && tMsgEligCoaSegPtrnArr.length() > 0) {
                int j = 0;
                for (int i = tMsgCoaBrArr.length(); i < (tMsgCoaBrArr.length() + tMsgEligCoaSegPtrnArr.length()); i++) {
                    bizMsg.ajeCoaBrCd_1.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    bizMsg.ajeCoaBrCd_2.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    j++;
                }
            }
        }
    }
    */

    private void setAjeLineIndTpListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajeLineIndTpCd_1.clear();
        bizMsg.ajeLineIndTpNm_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // AJE_LINE_IND_TP_SORT_NUM ASC
        // ]]></query>
        AJE_LINE_IND_TPTMsg tMsg = new AJE_LINE_IND_TPTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        AJE_LINE_IND_TPTMsgArray tMsgArr = (AJE_LINE_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.ajeLineIndTpCd_1.no(i).setValue(tMsgArr.no(i).ajeLineIndTpCd.getValue());
                bizMsg.ajeLineIndTpNm_2.no(i).setValue(tMsgArr.no(i).ajeLineIndTpNm.getValue());
            }
        }
    }

    private void setChCdListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajeCoaChCd_1.clear();
        bizMsg.ajeCoaChCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // COA_CH_CD ASC
        COA_CHTMsg tMsgCoaCh = new COA_CHTMsg();
        tMsgCoaCh.setSQLID("801");
        tMsgCoaCh.setConditionValue("glblCmpyCd01", globalCompanyCode);
        COA_CHTMsgArray tMsgCoaChArr = (COA_CHTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaCh);

        if (tMsgCoaChArr != null && tMsgCoaChArr.length() > 0) {
            for (int i = 0; i < tMsgCoaChArr.length(); i++) {
                bizMsg.ajeCoaChCd_1.no(i).setValue(tMsgCoaChArr.no(i).coaChCd.getValue());
                bizMsg.ajeCoaChCd_2.no(i).setValue(tMsgCoaChArr.no(i).coaChCd.getValue());
            }
            // <ID>801</ID>
            // <query><![CDATA[
            // WHERE
            // GLBL_CMPY_CD = ?glblCmpyCd01? AND
            // EZCANCELFLAG = '0' AND
            // ELIG_COA_SEG_PTRN_CD = ?eligCoaSegPtrnCd01?
            ELIG_COA_SEG_PTRNTMsg tMsgEligCoaSegPtrn = new ELIG_COA_SEG_PTRNTMsg();
            tMsgEligCoaSegPtrn.setSQLID("801");
            tMsgEligCoaSegPtrn.setConditionValue("glblCmpyCd01", globalCompanyCode);
            tMsgEligCoaSegPtrn.setConditionValue("eligCoaSegPtrnCd01", COA_CH_CD);
            ELIG_COA_SEG_PTRNTMsgArray tMsgEligCoaSegPtrnArr = (ELIG_COA_SEG_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsgEligCoaSegPtrn);

            if (tMsgEligCoaSegPtrnArr != null && tMsgEligCoaSegPtrnArr.length() > 0) {
                int j = 0;
                for (int i = tMsgCoaChArr.length(); i < (tMsgCoaChArr.length() + tMsgEligCoaSegPtrnArr.length()); i++) {
                    bizMsg.ajeCoaChCd_1.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    bizMsg.ajeCoaChCd_2.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    j++;
                }
            }
        }
    }

    private void setExtnCdListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.ajeCoaExtnCd_1.clear();
        bizMsg.ajeCoaExtnCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // COA_EXTN_CD ASC
        COA_EXTNTMsg tMsgCoaExtn = new COA_EXTNTMsg();
        tMsgCoaExtn.setSQLID("801");
        tMsgCoaExtn.setConditionValue("glblCmpyCd01", globalCompanyCode);
        COA_EXTNTMsgArray tMsgCoaExtnArr = (COA_EXTNTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaExtn);

        if (tMsgCoaExtnArr != null && tMsgCoaExtnArr.length() > 0) {
            for (int i = 0; i < tMsgCoaExtnArr.length(); i++) {
                bizMsg.ajeCoaExtnCd_1.no(i).setValue(tMsgCoaExtnArr.no(i).coaExtnCd.getValue());
                bizMsg.ajeCoaExtnCd_2.no(i).setValue(tMsgCoaExtnArr.no(i).coaExtnCd.getValue());
            }
            // <ID>801</ID>
            // <query><![CDATA[
            // WHERE
            // GLBL_CMPY_CD = ?glblCmpyCd01? AND
            // EZCANCELFLAG = '0' AND
            // ELIG_COA_SEG_PTRN_CD = ?eligCoaSegPtrnCd01?
            ELIG_COA_SEG_PTRNTMsg tMsgEligCoaSegPtrn = new ELIG_COA_SEG_PTRNTMsg();
            tMsgEligCoaSegPtrn.setSQLID("801");
            tMsgEligCoaSegPtrn.setConditionValue("glblCmpyCd01", globalCompanyCode);
            tMsgEligCoaSegPtrn.setConditionValue("eligCoaSegPtrnCd01", COA_EXTN_CD);
            ELIG_COA_SEG_PTRNTMsgArray tMsgEligCoaSegPtrnArr = (ELIG_COA_SEG_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsgEligCoaSegPtrn);

            if (tMsgEligCoaSegPtrnArr != null && tMsgEligCoaSegPtrnArr.length() > 0) {
                int j = 0;
                for (int i = tMsgCoaExtnArr.length(); i < (tMsgCoaExtnArr.length() + tMsgEligCoaSegPtrnArr.length()); i++) {
                    bizMsg.ajeCoaExtnCd_1.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    bizMsg.ajeCoaExtnCd_2.no(i).setValue(tMsgEligCoaSegPtrnArr.no(j).coaSegLkupTpCd.getValue());
                    j++;
                }
            }
        }
    }

    /**
     * Method name: getTrxRsnListBox
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Screen Component Interface Message
     */
    public void getTrxRsnListBox(NFAL0050CMsg bizMsg) {

        bizMsg.trxRsnCd_1.clear();
        bizMsg.trxRsnCd_2.clear();
        bizMsg.trxRsnCd_3.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        // ORDER BY
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxRsnCd_1.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
                bizMsg.trxRsnCd_2.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
            }
        }
    }

    /**
     * Method name: setSysSrcNm
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Screen Component Interface Message
     */
    public void setSysSrcNm(NFAL0050CMsg bizMsg) {

        bizMsg.sysSrcNm.clear();
        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // SYS_SRC_CD = ?sysSrcCd01?
        SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("sysSrcCd01", bizMsg.sysSrcCd_3.getValue());
        SYS_SRCTMsgArray tMsgArr = (SYS_SRCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.sysSrcNm.setValue(tMsgArr.no(0).sysSrcNm.getValue());
        }
    }

    /**
     * Method name: setTrxNm
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Screen Component Interface Message
     */
    public void setTrxNm(NFAL0050CMsg bizMsg) {

        bizMsg.trxNm.clear();
        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        TRXTMsg tMsg = new TRXTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRXTMsgArray tMsgArr = (TRXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.trxNm.setValue(tMsgArr.no(0).trxNm.getValue());
        }
    }

    /**
     * Method name: setTrxRsnNm
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizMsg Screen Component Interface Message
     */
    public void setTrxRsnNm(NFAL0050CMsg bizMsg) {

        bizMsg.trxRsnNm.clear();
        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01? AND
        // TRX_RSN_CD = ?trxRsnCd01?
        // ORDER BY
        // TRX_CD ASC,
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        tMsg.setConditionValue("trxRsnCd01", bizMsg.trxRsnCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.trxRsnNm.setValue(tMsgArr.no(0).trxRsnNm.getValue());
        }
    }

    /**
     * Return AJE_LINE_IND_TP_NM based on AJE_LINE_IND_TP_CD
     * @param bizMsg Screen Component Interface Message
     * @param lineIndTpCdParam LINE_IND_TP_CD
     */
    private String getLineIndTpNm(NFAL0050CMsg bizMsg, String lineIndTpCdParam) {
        for (int i = 0; i < bizMsg.ajeLineIndTpCd_1.length(); i++) {
            if (bizMsg.ajeLineIndTpCd_1.no(i).getValue().equals(lineIndTpCdParam)) {
                return bizMsg.ajeLineIndTpNm_2.no(i).getValue();
            }
        }
        return BLANK;
    }

    /**
     * Method name: setResetFields
     * <dd>The method explanation: Set values for indicator type for
     * re-set operation
     * <dd>Remarks:
     * @param bizMsg Screen Component Interface Message
     */
    public void setResetFields(NFAL0050CMsg bizMsg) {

        bizMsg.ajeId_T.setValue(bizMsg.ajeId.getValue());
        bizMsg.ajePtrnIndTpNm_1T.setValue(bizMsg.ajePtrnIndTpCd_1V.getValue());
        bizMsg.ajePtrnIndTpNm_2T.setValue(bizMsg.ajePtrnIndTpCd_2V.getValue());
        bizMsg.ajePtrnIndTpNm_3T.setValue(bizMsg.ajePtrnIndTpCd_3V.getValue());
        bizMsg.ajePtrnActlNm_1T.setValue(bizMsg.ajePtrnActlCd_1V.getValue());
        bizMsg.ajePtrnActlNm_2T.setValue(bizMsg.ajePtrnActlCd_2V.getValue());
        bizMsg.ajePtrnActlNm_3T.setValue(bizMsg.ajePtrnActlCd_3V.getValue());
    }

    /**
     * Method name: allIndTpNotBlank
     * <dd>The method explanation: Check if six indicator type are
     * all blank
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean allIndTpNotBlank(NFAL0050CMsg scrnMsg) {
        if (!scrnMsg.ajePtrnIndTpCd_1V.isClear() // Type_1
                && !scrnMsg.ajePtrnIndTpCd_2V.isClear() // Type_2
                && !scrnMsg.ajePtrnIndTpCd_3V.isClear() // Type_3
                && !scrnMsg.ajePtrnActlCd_1V.isClear() // Code_1
                && !scrnMsg.ajePtrnActlCd_2V.isClear() // Code_2
                && !scrnMsg.ajePtrnActlCd_3V.isClear()) { // Code_3
            return true;
        }
        return false;
    }

    /**
     * Method name: allIndTpSelected
     * <dd>The method explanation: Check if six indicator type are
     * neither blank nor default
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean sixIndTpSelected(NFAL0050CMsg scrnMsg) {
        if ((!scrnMsg.ajePtrnIndTpCd_1V.isClear() && !scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_1
                && (!scrnMsg.ajePtrnActlCd_1V.isClear() && !scrnMsg.ajePtrnActlCd_1V.getValue().equals(DEFAULT_VAL_CD_2)) // Code_1
                && (!scrnMsg.ajePtrnIndTpCd_2V.isClear() && !scrnMsg.ajePtrnIndTpCd_2V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_2
                && (!scrnMsg.ajePtrnActlCd_2V.isClear() && !scrnMsg.ajePtrnActlCd_2V.getValue().equals(DEFAULT_VAL_CD_2)) // Code_2
                && (!scrnMsg.ajePtrnIndTpCd_3V.isClear() && !scrnMsg.ajePtrnIndTpCd_3V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_3
                && (!scrnMsg.ajePtrnActlCd_3V.isClear()) && !scrnMsg.ajePtrnActlCd_3V.getValue().equals(DEFAULT_VAL_CD_2)) { // Code_3

            return true;
        }
        return false;
    }

    /**
     * Method name: fourIndTpSelected
     * <dd>The method explanation: Check if four indicator type are
     * neither blank nor default
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean fourIndTpSelected(NFAL0050CMsg scrnMsg) {
        if ((!scrnMsg.ajePtrnIndTpCd_1V.isClear() && !scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_1
                && (!scrnMsg.ajePtrnActlCd_1V.isClear() && !scrnMsg.ajePtrnActlCd_1V.getValue().equals(DEFAULT_VAL_CD_2)) // Code_1
                && (!scrnMsg.ajePtrnIndTpCd_2V.isClear() && !scrnMsg.ajePtrnIndTpCd_2V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_2
                && (!scrnMsg.ajePtrnActlCd_2V.isClear() && !scrnMsg.ajePtrnActlCd_2V.getValue().equals(DEFAULT_VAL_CD_2)) // Code_2
                && (!scrnMsg.ajePtrnIndTpCd_3V.isClear() && scrnMsg.ajePtrnIndTpCd_3V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_3
                && (!scrnMsg.ajePtrnActlCd_3V.isClear()) && scrnMsg.ajePtrnActlCd_3V.getValue().equals(DEFAULT_VAL_CD_2)) { // Code_3
            return true;
        }
        return false;
    }
    
    /**
     * Journal Category Validation
     * @param bizMsg
     * @return  boolean
     */
    public boolean jrnlCatgSearch(NFAL0050CMsg bizMsg) {
        JRNL_CATGTMsg tMsg = new JRNL_CATGTMsg();

        setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(tMsg.jrnlCatgCd, bizMsg.jrnlCatgCd.getValue());

        JRNL_CATGTMsg rslt = (JRNL_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (rslt == null) {
            return false;
        } else {
            setValue(bizMsg.jrnlCatgNm, rslt.jrnlCatgNm.getValue());
            return true;
        }

    }
}
