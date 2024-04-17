/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0050;

import java.text.DecimalFormat;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0050.common.NFAL0050CommonLogic;
import business.blap.NFAL0050.constant.NFAL0050Constant;
import business.db.AJE_PTRNTMsg;
import business.db.AJE_PTRNTMsgArray;
import business.db.COA_SEG_FRT_CHRG_PTRNTMsg;
import business.db.COA_SEG_PROD_PTRNTMsg;
import business.db.COA_SEG_WH_PTRNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * Class name: NFAL0050BL04
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0050BL04
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050BL06 extends S21BusinessHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0050Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_CMN_Delete(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_CMN_Delete
     * <dd>The method explanation: Business processing for Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CMN_Delete================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        // For Mutual Exclusion
        if (isUpdated(bizMsg)) {
            bizMsg.setMessageInfo("NFAM0004E");
            return;
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);
        if (numberOfAjePtrn > 0) { // Record Exist (to delete)

            // Delete Aje Pattern Table
            deleteDisplayedPtrnByIndTp(bizMsg);
            // Delete Seg Pattern Tables
            // deleteSegPtrn(bizMsg);
        } else {
            bizMsg.setMessageInfo("ZZXM0001E", new String[] {"No record to detele" });
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CMN_Delete================================END", null);
    }

    private void deleteSegPtrn(NFAL0050CMsg bizMsg) {

        if (!checkAjePtrnWithSegPtrn(bizMsg, PR)) {
            // There is NO specified AJE ID with #PR
            // in AJE_PTRN, delete
            // COA_SEG_PROD_PTRN
            deleteCoaSegProdPtrn(bizMsg);
        }
        // if (!checkAjePtrnWithSegPtrn(bizMsg, PRBR)) {
        // // There is NO specified AJE ID with #PRBR
        // // in AJE_PTRN, delete
        // // COA_SEG_PROD_BR_PTRN
        // deleteCoaSegProdBrPtrn(bizMsg);
        // }
        if (!checkAjePtrnWithSegPtrn(bizMsg, WH)) {
            // There is NO specified AJE ID with #WH
            // in AJE_PTRN, delete
            // COA_SEG_WH_PTRN
            deleteCoaSegWhPtrn(bizMsg);
        }
        if (!checkAjePtrnWithSegPtrn(bizMsg, BLANK)) {
            // There is NO specified AJE ID
            // in AJE_PTRN, delete
            // COA_SEG_PROD_PTRN and
            // COA_SEG_PROD_BR_PTRN and
            // COA_SEG_WH_PTRN and
            // COA_SEG_FRT_CHRG_PTRNT
            deleteCoaSegProdPtrn(bizMsg);
            // deleteCoaSegProdBrPtrn(bizMsg);
            deleteCoaSegWhPtrn(bizMsg);
            deleteCoaSegFrtChrgBrPtrn(bizMsg);
        }
    }

    private void deleteCoaSegProdPtrn(NFAL0050CMsg bizMsg) {

        COA_SEG_PROD_PTRNTMsg tMsgDel = new COA_SEG_PROD_PTRNTMsg();

        tMsgDel.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsgDel.sysSrcCd.setValue(bizMsg.sysSrcCd_3.getValue());
        tMsgDel.trxCd.setValue(bizMsg.trxCd_3.getValue());
        tMsgDel.trxRsnCd.setValue(bizMsg.trxRsnCd_3.getValue());

        EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDel);
    }

    // private void deleteCoaSegProdBrPtrn(NFAL0050CMsg bizMsg) {
    //
    // COA_SEG_PROD_BR_PTRNTMsg tMsgDel = new
    // COA_SEG_PROD_BR_PTRNTMsg();
    //
    // tMsgDel.glblCmpyCd.setValue(this.getGlobalCompanyCode());
    // tMsgDel.sysSrcCd.setValue(bizMsg.sysSrcCd_3.getValue());
    // tMsgDel.trxCd.setValue(bizMsg.trxCd_3.getValue());
    // tMsgDel.trxRsnCd.setValue(bizMsg.trxRsnCd_3.getValue());
    //
    // EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDel);
    // }

    private void deleteCoaSegWhPtrn(NFAL0050CMsg bizMsg) {

        COA_SEG_WH_PTRNTMsg tMsgDel = new COA_SEG_WH_PTRNTMsg();

        tMsgDel.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsgDel.sysSrcCd.setValue(bizMsg.sysSrcCd_3.getValue());
        tMsgDel.trxCd.setValue(bizMsg.trxCd_3.getValue());
        tMsgDel.trxRsnCd.setValue(bizMsg.trxRsnCd_3.getValue());

        EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDel);
    }

    private void deleteCoaSegFrtChrgBrPtrn(NFAL0050CMsg bizMsg) {

        COA_SEG_FRT_CHRG_PTRNTMsg tMsgDel = new COA_SEG_FRT_CHRG_PTRNTMsg();

        tMsgDel.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsgDel.sysSrcCd.setValue(bizMsg.sysSrcCd_3.getValue());
        tMsgDel.trxCd.setValue(bizMsg.trxCd_3.getValue());
        tMsgDel.trxRsnCd.setValue(bizMsg.trxRsnCd_3.getValue());

        EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDel);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_CMN_Submit
     * <dd>The method explanation: Business processing for Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CMN_Submit================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        //---- start add 2016/01/22
        if (!common.jrnlCatgSearch(bizMsg)) {
            bizMsg.jrnlCatgCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.jrnlCatgCd.getValue(), "Journal Category Code" });
            return;
        }
        //---- end 2016/01/22
        
        // For Mutual Exclusion
        if (isUpdated(bizMsg)) {
            bizMsg.setMessageInfo("NFAM0004E");
            return;
        }
        long nonDefPtrn = countNonDefPtrn(bizMsg);
        if (sixIndTpAllDefault(bizMsg)) {
            if (nonDefPtrn > 0) {
                // At least one non-Default record(s) already exist
                bizMsg.setMessageInfo("NFAM0062E", new String[] {Long.toString(nonDefPtrn), "AJE ID:" + bizMsg.ajeId.getValue(), "Non-Default Pattern" });
                return;
            }
        }
        // Clean old records before inserted
        deleteDisplayedPtrnByIndTp(bizMsg);

        if (nonDefPtrn == 0) {
            // Delete default pattern when default pattern
            // is the only pattern
            // i.e. User try to add non default pattern then
            // default pattern will not be needed.
            //
            // On the other hand, not delete default pattern when
            // at leaset one non-default pattern already exist
            // because a user might have added the default pattern
            // intentionally even if non-default pattern exist.
            deleteDefaultPtrn(bizMsg);
        }

        updateOrInsertAjePtrnList(cMsg);
        common.setSearchedResult(bizMsg, NOT_RESET);
        common.setResetFields(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CMN_Submit================================END", null);
    }

    private boolean isUpdated(NFAL0050CMsg bizMsg) {

        AJE_PTRNTMsgArray tMsgArrExist = findExistingRecord(bizMsg);

        String beforeEzUpTime = bizMsg.ezUpTime.getValue();
        String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();

        if (beforeEzUpTime.equals(BLANK) || beforeEzUpTimeZone.equals(BLANK)) {
            // EzUpTime OR EzUpTimeZone was/were
            // originally null (Inconsistent data)
            return false;
        } else {
            if (tMsgArrExist != null && tMsgArrExist.length() > 0) {
                // Record updated or unchanged
                String currentEzUpTime = tMsgArrExist.no(0).ezUpTime.getValue();
                String currentEzUpTimeZone = tMsgArrExist.no(0).ezUpTimeZone.getValue();
                if (ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    // record is not updated /deleted
                    return false;
                } else {
                    // record updated or deleted by somebody else
                    return true;
                }
            } else {
                // Record doesn't exist means deleted by somebody else
                return true;
            }
        }
    }

    private AJE_PTRNTMsgArray findExistingRecord(NFAL0050CMsg bizMsg) {

        AJE_PTRNTMsg tMsg = new AJE_PTRNTMsg();
        // Logically exist or not
        // <ID>804</ID>
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
        tMsg.setSQLID("804");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("ajeId01", bizMsg.ajeId.getValue());
        tMsg.setConditionValue("ajePtrnIndTpCd0101", bizMsg.ajePtrnIndTpCd_1V.getValue());
        tMsg.setConditionValue("ajePtrnActlCd0101", bizMsg.ajePtrnActlCd_1V.getValue());
        tMsg.setConditionValue("ajePtrnIndTpCd0201", bizMsg.ajePtrnIndTpCd_2V.getValue());
        tMsg.setConditionValue("ajePtrnActlCd0201", bizMsg.ajePtrnActlCd_2V.getValue());
        tMsg.setConditionValue("ajePtrnIndTpCd0301", bizMsg.ajePtrnIndTpCd_3V.getValue());
        tMsg.setConditionValue("ajePtrnActlCd0301", bizMsg.ajePtrnActlCd_3V.getValue());

        return (AJE_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    private long countNonDefPtrn(NFAL0050CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getResultCountNonDefault(bizMsg);
        if (ssmResult.isCodeNormal()) {
            return Long.parseLong(new DecimalFormat("0").format(bizMsg.xxTotCnt_D.getValue()));
        } else {
            return 0;
        }
    }

    /**
     * Method name: sixIndTpAllDefault
     * <dd>The method explanation: Button Control.
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @return boolean
     */
    public boolean sixIndTpAllDefault(NFAL0050CMsg scrnMsg) {
        if ((!scrnMsg.ajePtrnIndTpCd_1V.isClear() && scrnMsg.ajePtrnIndTpCd_1V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_1
                && (!scrnMsg.ajePtrnActlCd_1V.isClear() && scrnMsg.ajePtrnActlCd_1V.getValue().equals(DEFAULT_VAL_CD_2)) // Code_1
                && (!scrnMsg.ajePtrnIndTpCd_2V.isClear() && scrnMsg.ajePtrnIndTpCd_2V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_2
                && (!scrnMsg.ajePtrnActlCd_2V.isClear() && scrnMsg.ajePtrnActlCd_2V.getValue().equals(DEFAULT_VAL_CD_2)) // Code_2
                && (!scrnMsg.ajePtrnIndTpCd_3V.isClear() && scrnMsg.ajePtrnIndTpCd_3V.getValue().equals(DEFAULT_VAL_CD_3)) // Type_3
                && (!scrnMsg.ajePtrnActlCd_3V.isClear()) && scrnMsg.ajePtrnActlCd_3V.getValue().equals(DEFAULT_VAL_CD_2)) { // Code_3
            return true;
        }
        return false;
    }

    private void deleteDisplayedPtrnByIndTp(NFAL0050CMsg bizMsg) {

        AJE_PTRNTMsg tMsgDelete = new AJE_PTRNTMsg();
        tMsgDelete.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsgDelete.ajeId.setValue(bizMsg.ajeId.getValue());

        tMsgDelete.ajePtrnIndTpCd_01.setValue(bizMsg.ajePtrnIndTpCd_1V.getValue());
        tMsgDelete.ajePtrnActlCd_01.setValue(bizMsg.ajePtrnActlCd_1V.getValue());

        tMsgDelete.ajePtrnIndTpCd_02.setValue(bizMsg.ajePtrnIndTpCd_2V.getValue());
        tMsgDelete.ajePtrnActlCd_02.setValue(bizMsg.ajePtrnActlCd_2V.getValue());

        tMsgDelete.ajePtrnIndTpCd_03.setValue(bizMsg.ajePtrnIndTpCd_3V.getValue());
        tMsgDelete.ajePtrnActlCd_03.setValue(bizMsg.ajePtrnActlCd_3V.getValue());

        EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDelete);
    }

    private void deleteDefaultPtrn(NFAL0050CMsg bizMsg) {

        AJE_PTRNTMsg tMsgDelete = new AJE_PTRNTMsg();
        tMsgDelete.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsgDelete.ajeId.setValue(bizMsg.ajeId.getValue());

        tMsgDelete.ajePtrnIndTpCd_01.setValue(DEFAULT_VAL_CD_3);
        tMsgDelete.ajePtrnIndTpCd_02.setValue(DEFAULT_VAL_CD_3);
        tMsgDelete.ajePtrnIndTpCd_03.setValue(DEFAULT_VAL_CD_3);

        tMsgDelete.ajePtrnActlCd_01.setValue(DEFAULT_VAL_CD_2);
        tMsgDelete.ajePtrnActlCd_02.setValue(DEFAULT_VAL_CD_2);
        tMsgDelete.ajePtrnActlCd_03.setValue(DEFAULT_VAL_CD_2);

        EZDTBLAccessor.logicalRemoveByPartialKey(tMsgDelete);
    }

    private void updateOrInsertAjePtrnList(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        if (bizMsg.A.getValidCount() > 0) {

            String ajePtrnIndTpNm01 = getIndTpNm01(bizMsg);
            String ajePtrnIndTpNm02 = getIndTpNm02(bizMsg);
            String ajePtrnIndTpNm03 = getIndTpNm03(bizMsg);
            String ajePtrnActlNm01 = getActlNm01(bizMsg);
            String ajePtrnActlNm02 = getActlNm02(bizMsg);
            String ajePtrnActlNm03 = getActlNm03(bizMsg);

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                AJE_PTRNTMsg tMsg = new AJE_PTRNTMsg();

                tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                tMsg.ajeId.setValue(bizMsg.ajeId.getValue());

                tMsg.ajePtrnIndTpCd_01.setValue(bizMsg.ajePtrnIndTpCd_1V.getValue());
                tMsg.ajePtrnActlCd_01.setValue(bizMsg.ajePtrnActlCd_1V.getValue());
                tMsg.ajePtrnIndTpCd_02.setValue(bizMsg.ajePtrnIndTpCd_2V.getValue());
                tMsg.ajePtrnActlCd_02.setValue(bizMsg.ajePtrnActlCd_2V.getValue());
                tMsg.ajePtrnIndTpCd_03.setValue(bizMsg.ajePtrnIndTpCd_3V.getValue());
                tMsg.ajePtrnActlCd_03.setValue(bizMsg.ajePtrnActlCd_3V.getValue());

                tMsg.ajeLineIdxNum.setValue(bizMsg.A.no(i).ajeLineIdxNum_A.getValue());
                tMsg.drCrTpCd.setValue(bizMsg.A.no(i).drCrTpCd_A.getValue());

                tMsg = (AJE_PTRNTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

                if (tMsg == null) {
                    AJE_PTRNTMsg tMsgInsert = new AJE_PTRNTMsg();

                    tMsgInsert.sysSrcCd.setValue(bizMsg.sysSrcCd_3.getValue());
                    tMsgInsert.trxCd.setValue(bizMsg.trxCd_3.getValue());
                    tMsgInsert.trxRsnCd.setValue(bizMsg.trxRsnCd_3.getValue());

                    tMsgInsert.sysSrcNm.setValue(bizMsg.sysSrcNm.getValue());
                    tMsgInsert.trxNm.setValue(bizMsg.trxNm.getValue());
                    tMsgInsert.trxRsnNm.setValue(bizMsg.trxRsnNm.getValue());

                    tMsgInsert.ajePtrnIndTpNm_01.setValue(ajePtrnIndTpNm01);
                    tMsgInsert.ajePtrnActlNm_01.setValue(ajePtrnActlNm01);
                    tMsgInsert.ajePtrnIndTpNm_02.setValue(ajePtrnIndTpNm02);
                    tMsgInsert.ajePtrnActlNm_02.setValue(ajePtrnActlNm02);
                    tMsgInsert.ajePtrnIndTpNm_03.setValue(ajePtrnIndTpNm03);
                    tMsgInsert.ajePtrnActlNm_03.setValue(ajePtrnActlNm03);

                    tMsgInsert.jrnlCatgCd.setValue(bizMsg.jrnlCatgCd.getValue());
                    tMsgInsert.jrnlCatgNm.setValue(bizMsg.jrnlCatgNm.getValue());

                    tMsgInsert.ajeLinkFlg.setValue(FLG_Y);
                    tMsgInsert.ajeCoaCmpyCd.setValue(bizMsg.A.no(i).ajeCoaCmpyCd_A.getValue());
                    tMsgInsert.ajeCoaBrCd.setValue(bizMsg.A.no(i).ajeCoaBrCd_A.getValue());
                    tMsgInsert.ajeCoaCcCd.setValue(bizMsg.A.no(i).ajeCoaCcCd_A.getValue());
                    tMsgInsert.ajeCoaAcctCd.setValue(bizMsg.A.no(i).ajeCoaAcctCd_A.getValue());
                    tMsgInsert.ajeLineIndTpCd.setValue(bizMsg.A.no(i).ajeLineIndTpCd_A.getValue());
                    tMsgInsert.ajeLineIdxDescTxt.setValue(bizMsg.A.no(i).ajeLineIdxDescTxt_A.getValue());
                    tMsgInsert.ajeCoaProdCd.setValue(bizMsg.A.no(i).ajeCoaProdCd_A.getValue());
                    tMsgInsert.ajeCoaChCd.setValue(bizMsg.A.no(i).ajeCoaChCd_A.getValue());
                    tMsgInsert.ajeCoaAfflCd.setValue(bizMsg.A.no(i).ajeCoaAfflCd_A.getValue());
                    tMsgInsert.ajeCoaProjCd.setValue(bizMsg.A.no(i).ajeCoaProjCd_A.getValue());
                    tMsgInsert.ajeCoaExtnCd.setValue(bizMsg.A.no(i).ajeCoaExtnCd_A.getValue());

                    // primary key(s) for the condition
                    tMsgInsert.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                    tMsgInsert.ajeId.setValue(bizMsg.ajeId.getValue());

                    tMsgInsert.ajePtrnIndTpCd_01.setValue(bizMsg.ajePtrnIndTpCd_1V.getValue());
                    tMsgInsert.ajePtrnActlCd_01.setValue(bizMsg.ajePtrnActlCd_1V.getValue());
                    tMsgInsert.ajePtrnIndTpCd_02.setValue(bizMsg.ajePtrnIndTpCd_2V.getValue());
                    tMsgInsert.ajePtrnActlCd_02.setValue(bizMsg.ajePtrnActlCd_2V.getValue());
                    tMsgInsert.ajePtrnIndTpCd_03.setValue(bizMsg.ajePtrnIndTpCd_3V.getValue());
                    tMsgInsert.ajePtrnActlCd_03.setValue(bizMsg.ajePtrnActlCd_3V.getValue());

                    tMsgInsert.ajeLineIdxNum.setValue(bizMsg.A.no(i).ajeLineIdxNum_A.getValue());
                    tMsgInsert.drCrTpCd.setValue(bizMsg.A.no(i).drCrTpCd_A.getValue());
                    
                    //---- start add 2016/01/22
                    setValue(tMsgInsert.jrnlAmtFlipFlg, getChkBoxVal(bizMsg.xxChkBox_AM.getValue()));
                    setValue(tMsgInsert.jrnlQtyFlipFlg, getChkBoxVal(bizMsg.xxChkBox_QT.getValue()));
                    //---- end 2016/01/22

                    EZDTBLAccessor.create(tMsgInsert);
                } else {

                    tMsg.sysSrcCd.setValue(bizMsg.sysSrcCd_3.getValue());
                    tMsg.trxCd.setValue(bizMsg.trxCd_3.getValue());
                    tMsg.trxRsnCd.setValue(bizMsg.trxRsnCd_3.getValue());

                    tMsg.sysSrcNm.setValue(bizMsg.sysSrcNm.getValue());
                    tMsg.trxNm.setValue(bizMsg.trxNm.getValue());
                    tMsg.trxRsnNm.setValue(bizMsg.trxRsnNm.getValue());

                    tMsg.ajePtrnIndTpNm_01.setValue(ajePtrnIndTpNm01);
                    tMsg.ajePtrnActlNm_01.setValue(ajePtrnActlNm01);
                    tMsg.ajePtrnIndTpNm_02.setValue(ajePtrnIndTpNm02);
                    tMsg.ajePtrnActlNm_02.setValue(ajePtrnActlNm02);
                    tMsg.ajePtrnIndTpNm_03.setValue(ajePtrnIndTpNm03);
                    tMsg.ajePtrnActlNm_03.setValue(ajePtrnActlNm03);

                    tMsg.jrnlCatgCd.setValue(bizMsg.jrnlCatgCd.getValue());
                    tMsg.jrnlCatgNm.setValue(bizMsg.jrnlCatgNm.getValue());

                    tMsg.ajeLinkFlg.setValue(FLG_Y);
                    tMsg.ajeCoaCmpyCd.setValue(bizMsg.A.no(i).ajeCoaCmpyCd_A.getValue());
                    tMsg.ajeCoaBrCd.setValue(bizMsg.A.no(i).ajeCoaBrCd_A.getValue());
                    tMsg.ajeCoaCcCd.setValue(bizMsg.A.no(i).ajeCoaCcCd_A.getValue());
                    tMsg.ajeCoaAcctCd.setValue(bizMsg.A.no(i).ajeCoaAcctCd_A.getValue());
                    tMsg.ajeLineIndTpCd.setValue(bizMsg.A.no(i).ajeLineIndTpCd_A.getValue());
                    tMsg.ajeLineIdxDescTxt.setValue(bizMsg.A.no(i).ajeLineIdxDescTxt_A.getValue());
                    tMsg.ajeCoaProdCd.setValue(bizMsg.A.no(i).ajeCoaProdCd_A.getValue());
                    tMsg.ajeCoaChCd.setValue(bizMsg.A.no(i).ajeCoaChCd_A.getValue());
                    tMsg.ajeCoaAfflCd.setValue(bizMsg.A.no(i).ajeCoaAfflCd_A.getValue());
                    tMsg.ajeCoaProjCd.setValue(bizMsg.A.no(i).ajeCoaProjCd_A.getValue());
                    tMsg.ajeCoaExtnCd.setValue(bizMsg.A.no(i).ajeCoaExtnCd_A.getValue());

                    //---- start add 2016/01/22
                    setValue(tMsg.jrnlAmtFlipFlg, getChkBoxVal(bizMsg.xxChkBox_AM.getValue()));
                    setValue(tMsg.jrnlQtyFlipFlg, getChkBoxVal(bizMsg.xxChkBox_QT.getValue()));
                    //---- end 2016/01/22
                    
                    EZDTBLAccessor.update(tMsg);
                }
            }
        }
    }

    //---- start add 2016/01/22
    private String getChkBoxVal(String val) {
        if (ZYPConstant.CHKBOX_ON_Y.equals(val)) {
            return ZYPConstant.CHKBOX_ON_Y;
        }
       
        return ZYPConstant.FLG_OFF_N;
    }
    //---- end 2016/01/22
    
    private boolean checkAjePtrnWithSegPtrn(NFAL0050CMsg bizMsg, String segPtrn) {

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().checkAjePtrnWithSegPtrn(bizMsg, segPtrn);
        if (ssmResult.isCodeNormal()) {
            return true;
        } else {
            return false;
        }
    }

    private String getIndTpNm01(NFAL0050CMsg bizMsg) {
        for (int i = 0; i < bizMsg.ajePtrnIndTpCd_1C.length(); i++) {
            if (bizMsg.ajePtrnIndTpCd_1C.no(i).getValue().equals(bizMsg.ajePtrnIndTpCd_1V.getValue())) {
                return bizMsg.ajePtrnIndTpNm_1D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getIndTpNm02(NFAL0050CMsg bizMsg) {
        for (int i = 0; i < bizMsg.ajePtrnIndTpCd_2C.length(); i++) {
            if (bizMsg.ajePtrnIndTpCd_2C.no(i).getValue().equals(bizMsg.ajePtrnIndTpCd_2V.getValue())) {
                return bizMsg.ajePtrnIndTpNm_2D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getIndTpNm03(NFAL0050CMsg bizMsg) {
        for (int i = 0; i < bizMsg.ajePtrnIndTpCd_3C.length(); i++) {
            if (bizMsg.ajePtrnIndTpCd_3C.no(i).getValue().equals(bizMsg.ajePtrnIndTpCd_3V.getValue())) {
                return bizMsg.ajePtrnIndTpNm_3D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getActlNm01(NFAL0050CMsg bizMsg) {
        for (int i = 0; i < bizMsg.ajePtrnActlCd_1C.length(); i++) {
            if (bizMsg.ajePtrnActlCd_1C.no(i).getValue().equals(bizMsg.ajePtrnActlCd_1V.getValue())) {
                return bizMsg.ajePtrnActlNm_1D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getActlNm02(NFAL0050CMsg bizMsg) {
        for (int i = 0; i < bizMsg.ajePtrnActlCd_2C.length(); i++) {
            if (bizMsg.ajePtrnActlCd_2C.no(i).getValue().equals(bizMsg.ajePtrnActlCd_2V.getValue())) {
                return bizMsg.ajePtrnActlNm_2D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getActlNm03(NFAL0050CMsg bizMsg) {
        for (int i = 0; i < bizMsg.ajePtrnActlCd_3C.length(); i++) {
            if (bizMsg.ajePtrnActlCd_3C.no(i).getValue().equals(bizMsg.ajePtrnActlCd_3V.getValue())) {
                return bizMsg.ajePtrnActlNm_3D.no(i).getValue();
            }
        }
        return BLANK;
    }
}
