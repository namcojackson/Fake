/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810.common;

import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0810.NSAL0810BMsg;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/03/21   Hitachi         K.Yamada        Update          CSA QC#5541
 * 2016/04/04   Hitachi         T.Iwamoto       Update          QC#5822
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/07/20   Hitachi         Y.Takeno        Update          QC#11826
 * 2016/07/20   Hitachi         Y.Takeno        Update          QC#11840
 * 2016/07/21   Hitachi         Y.Takeno        Update          QC#11849
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 * 2017/01/18   Hitachi         K.Ochiai        Update          QC#16331
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0810CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0810BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0810BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);

    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0810BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0810BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        // START 2017/01/18 [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId()) {
            if (MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue())) {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
                handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
                handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
                handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            } else if (MODE_AFTER_SEARCH.equals(scrnMsg.xxModeCd.getValue()) || MODE_AFTER_UPLOAD.equals(scrnMsg.xxModeCd.getValue())) {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
                handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
                handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
                // START 2016/07/20 [QC#11840, MOD]
                // handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
                handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
                // START 2016/07/20 [QC#11840, MOD]
            }
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        }
        // END 2017/01/18 [QC#16331, MOD]
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0810BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0810BMsg scrnMsg) {
     // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // focus set add
        if(scrnMsg.A.getValidCount()>0){
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
        }else{
            scrnMsg.setFocusItem(scrnMsg.contrIntfcSrcTpCd_SS);
        }
     // END 2016/09/01 Y.Zhang [QC#11846, MOD]
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenTableFields(handler, scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Interface Maintenance (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0810BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0810BMsg scrnMsg) {
        if (hasUpdateFuncId()) {
            if (MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue())) {
                handler.setButtonEnabled("OpenForCorrection", false);
                handler.setButtonEnabled("ResubmitBatInterface", true);
                handler.setButtonEnabled("SubmitBatValidation", true);

                handler.setButtonEnabled("SelectAll", true);
                handler.setButtonEnabled("UnselectAll", true);

                handler.setButtonEnabled("ActualCounters", true);
                handler.setButtonEnabled("Tiers", true);
                handler.setButtonEnabled("AdditionalCharges", true);
                handler.setButtonEnabled("SalesCredits", true);

                handler.setButtonEnabled("ValidateData", true);
                handler.setButtonEnabled("ResubmitInterface", true);
            } else if (MODE_AFTER_SEARCH.equals(scrnMsg.xxModeCd.getValue()) || MODE_AFTER_UPLOAD.equals(scrnMsg.xxModeCd.getValue())) {
                handler.setButtonEnabled("OpenForCorrection", true);
                handler.setButtonEnabled("ResubmitBatInterface", false);
                handler.setButtonEnabled("SubmitBatValidation", false);

                handler.setButtonEnabled("SelectAll", true);
                handler.setButtonEnabled("UnselectAll", true);

                handler.setButtonEnabled("ActualCounters", true);
                handler.setButtonEnabled("Tiers", true);
                handler.setButtonEnabled("AdditionalCharges", true);
                handler.setButtonEnabled("SalesCredits", true);

                handler.setButtonEnabled("ValidateData", false);
                handler.setButtonEnabled("ResubmitInterface", false);
            } else {
                // Init
                handler.setButtonEnabled("OpenForCorrection", false);
                handler.setButtonEnabled("ResubmitBatInterface", false);
                handler.setButtonEnabled("SubmitBatValidation", false);

                handler.setButtonEnabled("SelectAll", false);
                handler.setButtonEnabled("UnselectAll", false);

                handler.setButtonEnabled("ActualCounters", false);
                handler.setButtonEnabled("Tiers", false);
                handler.setButtonEnabled("AdditionalCharges", false);
                handler.setButtonEnabled("SalesCredits", false);

                handler.setButtonEnabled("ValidateData", false);
                handler.setButtonEnabled("ResubmitInterface", false);
            }
            handler.setButtonEnabled("Upload", true);

            // START 2016/07/20 [QC#11826, ADD]
            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled("OpenForCorrection", false);
                handler.setButtonEnabled("ResubmitBatInterface", false);
                handler.setButtonEnabled("SubmitBatValidation", false);

                handler.setButtonEnabled("SelectAll", false);
                handler.setButtonEnabled("UnselectAll", false);

                handler.setButtonEnabled("ActualCounters", false);
                handler.setButtonEnabled("Tiers", false);
                handler.setButtonEnabled("AdditionalCharges", false);
                handler.setButtonEnabled("SalesCredits", false);

                handler.setButtonEnabled("ValidateData", false);
                handler.setButtonEnabled("ResubmitInterface", false);
            }
            // END   2016/07/20 [QC#11826, ADD]
        } else {
            handler.setButtonEnabled("OpenForCorrection", false);
            handler.setButtonEnabled("ResubmitBatInterface", false);
            handler.setButtonEnabled("SubmitBatValidation", false);

            handler.setButtonEnabled("Upload", false);

            handler.setButtonEnabled("SelectAll", false);
            handler.setButtonEnabled("UnselectAll", false);

            handler.setButtonEnabled("ActualCounters", false);
            handler.setButtonEnabled("Tiers", false);
            handler.setButtonEnabled("AdditionalCharges", false);
            handler.setButtonEnabled("SalesCredits", false);

            handler.setButtonEnabled("ValidateData", false);
            handler.setButtonEnabled("ResubmitInterface", false);
        }
        scrnMsg.xxSrchCnt_SP.setInputProtected(true);
        scrnMsg.xxSrchCnt_SR.setInputProtected(true);
        scrnMsg.xxSrchCnt_SU.setInputProtected(true);
        scrnMsg.xxSrchCnt_SI.setInputProtected(true);
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL0810BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL0810BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // mod start 2016/03/21 CSA Defect#5541
            scrnMsg.A.no(i).intfcErrMsgTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrProcStsCd_AS.setInputProtected(true);
            scrnMsg.A.no(i).dsContrProcStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_AC.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_AB.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_AL.setInputProtected(true);
            scrnMsg.A.no(i).crCardCustRefNum_A.setInputProtected(true);
            scrnMsg.A.no(i).crCardExprYrMth_A.setInputProtected(true);
            // START 2016/05/25 T.Tomita [QC#8898, ADD]
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
            // END 2016/05/25 T.Tomita [QC#8898, ADD]

            if (hasUpdateFuncId() && MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue()) && !DS_CONTR_PROC_STS.COMPLEATED.equals(scrnMsg.A.no(i).dsContrProcStsCd_AS.getValue())) {
                scrnMsg.A.no(i).dsContrIntfcBatNum_A.setInputProtected(false);
                scrnMsg.A.no(i).dsContrSrcRefNum_A.setInputProtected(false);
                scrnMsg.A.no(i).contrIntfcSrcTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).dsContrNum_A.setInputProtected(false);
                scrnMsg.A.no(i).dsContrIntfcActCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_Machine", i, true);
                scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(false);
                scrnMsg.A.no(i).contrIntfcDtlTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).dsContrIntfcStsCd_A.setInputProtected(false);
                scrnMsg.A.no(i).dsAcctNum_A.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_CustAcctNum", i, true);
                scrnMsg.A.no(i).billToCustCd_A.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_BillToCustNum", i, true);
                scrnMsg.A.no(i).leaseCmpyCd_A.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_LeaseCustNum", i, true);
                scrnMsg.A.no(i).svcContrBrCd_A.setInputProtected(false);
                // add start 2016/06/30 CSA Defect#10661
                scrnMsg.A.no(i).contrAdminPsnCd_A.setInputProtected(false);
                // add end 2016/06/30 CSA Defect#10661
                scrnMsg.A.no(i).tocCd_A.setInputProtected(false);
                scrnMsg.A.no(i).custPoNum_A.setInputProtected(false);
                scrnMsg.A.no(i).poDt_A.setInputProtected(false);
                scrnMsg.A.no(i).mtrEstTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).svcPgmMdseCd_A.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_Merchandise", i, true);
                scrnMsg.A.no(i).mdlNm_A.setInputProtected(false);
                scrnMsg.A.no(i).contrFromDt_A.setInputProtected(false);
                scrnMsg.A.no(i).contrThruDt_A.setInputProtected(false);
                scrnMsg.A.no(i).bllgCycleCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).prcAllocByMachQtyFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).contrAutoRnwTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).rnwPrcMethCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).befEndRnwDaysAot_A.setInputProtected(false);
                scrnMsg.A.no(i).rnwPrcUpRatio_A.setInputProtected(false);
                scrnMsg.A.no(i).contrUplftTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).uplftPrcMethCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).uplftPrcUpRatio_A.setInputProtected(false);
                scrnMsg.A.no(i).mtrReadMethCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(false);
                scrnMsg.A.no(i).contrCloDay_A.setInputProtected(false);
                scrnMsg.A.no(i).contrBllgDay_A.setInputProtected(false);
                scrnMsg.A.no(i).bllgThruDt_A.setInputProtected(false);
                scrnMsg.A.no(i).bllgMtrLbCd_A.setInputProtected(false);
                // START 2016/05/25 T.Tomita [QC#8898, MOD]
//                scrnMsg.A.no(i).bllgMtrLbCd_AS.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_BllgMtr", i, true);
                // END 2016/05/25 T.Tomita [QC#8898, MOD]
                scrnMsg.A.no(i).startMtrCnt_A.setInputProtected(false);
                scrnMsg.A.no(i).bllgRollOverRatio_A.setInputProtected(false);
                scrnMsg.A.no(i).dsContrCatgCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).dsContrStsCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).xsChrgTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).xsMtrCopyQty_A.setInputProtected(false);
                scrnMsg.A.no(i).xsMtrAmtRate_A.setInputProtected(false);
                scrnMsg.A.no(i).addlChrgTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setInputProtected(false);
                scrnMsg.A.no(i).addlChrgAplcPct_A.setInputProtected(false);
                scrnMsg.A.no(i).chrgLvlTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).addlChrgInvTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).printDtlFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).basePrcTermDealAmtRate_A.setInputProtected(false);
                scrnMsg.A.no(i).dsContrClsCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).ctacPsnPk_A.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_NMAL6770", i, true);
                // START 2016/07/21 [QC#11849, MOD]
                // scrnMsg.A.no(i).ctacPsnNm_A.setInputProtected(false);
                scrnMsg.A.no(i).ctacPsnNm_A.setInputProtected(true);
                // END 2016/07/21 [QC#11849, MOD]
                // START 2022/03/22 [QC#59683, MOD]
//                scrnMsg.A.no(i).invSeptBaseUsgFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).dsInvTgtrTpCd_AS.setInputProtected(false);
                // END   2022/03/22 [QC#59683, MOD]
                scrnMsg.A.no(i).contrCloDt_A.setInputProtected(false);
                scrnMsg.A.no(i).contrDurnAot_A.setInputProtected(false);
                scrnMsg.A.no(i).pmtTermCashDiscCd_A.setInputProtected(false);
                scrnMsg.A.no(i).svcLineBizCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).bllgTmgTpCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).dsContrEdiCd_A.setInputProtected(false);
                scrnMsg.A.no(i).dsContrDescTxt_A.setInputProtected(false);
                scrnMsg.A.no(i).baseChrgToLeaseCmpyFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).usgChrgToLeaseCmpyFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).intgMdseCd_A.setInputProtected(false);
                scrnMsg.A.no(i).capBwOrigQty_A.setInputProtected(false);
                scrnMsg.A.no(i).capColorOrigQty_A.setInputProtected(false);
                scrnMsg.A.no(i).capTotOrigQty_A.setInputProtected(false);
                scrnMsg.A.no(i).capBwRunQty_A.setInputProtected(false);
                scrnMsg.A.no(i).capColorRunQty_A.setInputProtected(false);
                scrnMsg.A.no(i).capTotRunQty_A.setInputProtected(false);
                // START 2016/05/20 [QC#4061, ADD]
                scrnMsg.A.no(i).manContrOvrdFlg_A.setInputProtected(false);
                // END   2016/05/20 [QC#4061, ADD]
            } else {
                scrnMsg.A.no(i).dsContrIntfcBatNum_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrSrcRefNum_A.setInputProtected(true);
                scrnMsg.A.no(i).contrIntfcSrcTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrIntfcActCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_Machine", i, false);
                scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(true);
                scrnMsg.A.no(i).contrIntfcDtlTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).dsContrIntfcStsCd_A.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNum_A.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_CustAcctNum", i, false);
                scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_BillToCustNum", i, false);
                scrnMsg.A.no(i).leaseCmpyCd_A.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_LeaseCustNum", i, false);
                scrnMsg.A.no(i).svcContrBrCd_A.setInputProtected(true);
                // add start 2016/06/30 CSA Defect#10661
                scrnMsg.A.no(i).contrAdminPsnCd_A.setInputProtected(true);
                // add end 2016/06/30 CSA Defect#10661
                scrnMsg.A.no(i).tocCd_A.setInputProtected(true);
                scrnMsg.A.no(i).custPoNum_A.setInputProtected(true);
                scrnMsg.A.no(i).poDt_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrEstTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).svcPgmMdseCd_A.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_Merchandise", i, false);
                scrnMsg.A.no(i).mdlNm_A.setInputProtected(true);
                scrnMsg.A.no(i).contrFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).contrThruDt_A.setInputProtected(true);
                scrnMsg.A.no(i).bllgCycleCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).prcAllocByMachQtyFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).contrAutoRnwTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).rnwPrcMethCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).befEndRnwDaysAot_A.setInputProtected(true);
                scrnMsg.A.no(i).rnwPrcUpRatio_A.setInputProtected(true);
                scrnMsg.A.no(i).contrUplftTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).uplftPrcMethCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).uplftPrcUpRatio_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrReadMethCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(true);
                scrnMsg.A.no(i).contrCloDay_A.setInputProtected(true);
                scrnMsg.A.no(i).contrBllgDay_A.setInputProtected(true);
                scrnMsg.A.no(i).bllgThruDt_A.setInputProtected(true);
                scrnMsg.A.no(i).bllgMtrLbCd_A.setInputProtected(true);
                // START 2016/05/25 T.Tomita [QC#8898, MOD]
//                scrnMsg.A.no(i).bllgMtrLbCd_AS.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_BllgMtr", i, false);
                // END 2016/05/25 T.Tomita [QC#8898, MOD]
                scrnMsg.A.no(i).startMtrCnt_A.setInputProtected(true);
                scrnMsg.A.no(i).bllgRollOverRatio_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrCatgCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).dsContrStsCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).xsChrgTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).xsMtrCopyQty_A.setInputProtected(true);
                scrnMsg.A.no(i).xsMtrAmtRate_A.setInputProtected(true);
                scrnMsg.A.no(i).addlChrgTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setInputProtected(true);
                scrnMsg.A.no(i).addlChrgAplcPct_A.setInputProtected(true);
                scrnMsg.A.no(i).chrgLvlTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).addlChrgInvTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).printDtlFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).basePrcTermDealAmtRate_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrClsCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).ctacPsnPk_A.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_NMAL6770", i, false);
                scrnMsg.A.no(i).ctacPsnNm_A.setInputProtected(true);
                // START 2022/03/22 [QC#59683, MOD]
//                scrnMsg.A.no(i).invSeptBaseUsgFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).dsInvTgtrTpCd_AS.setInputProtected(true);
                // END   2022/03/22 [QC#59683, MOD]
                scrnMsg.A.no(i).contrCloDt_A.setInputProtected(true);
                scrnMsg.A.no(i).contrDurnAot_A.setInputProtected(true);
                scrnMsg.A.no(i).pmtTermCashDiscCd_A.setInputProtected(true);
                scrnMsg.A.no(i).svcLineBizCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).bllgTmgTpCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).dsContrEdiCd_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).baseChrgToLeaseCmpyFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).usgChrgToLeaseCmpyFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).intgMdseCd_A.setInputProtected(true);
                scrnMsg.A.no(i).capBwOrigQty_A.setInputProtected(true);
                scrnMsg.A.no(i).capColorOrigQty_A.setInputProtected(true);
                scrnMsg.A.no(i).capTotOrigQty_A.setInputProtected(true);
                scrnMsg.A.no(i).capBwRunQty_A.setInputProtected(true);
                scrnMsg.A.no(i).capColorRunQty_A.setInputProtected(true);
                scrnMsg.A.no(i).capTotRunQty_A.setInputProtected(true);
                // START 2016/05/20 [QC#4061, ADD]
                scrnMsg.A.no(i).manContrOvrdFlg_A.setInputProtected(true);
                // END   2016/05/20 [QC#4061, ADD]

            }
            // mod end 2016/03/21 CSA Defect#5541
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL0810BMsg
     * @param allCheckFlag boolean
     */
    public static final void addCheckItem(NSAL0810BMsg scrnMsg, boolean allCheckFlag) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_PROC_STS.COMPLEATED.equals(scrnMsg.A.no(i).dsContrProcStsCd_AS.getValue())) {
                if (!allCheckFlag) {
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                        continue;
                    }
                }
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrIntfcBatNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrSrcRefNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrIntfcSrcTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrIntfcActCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcMachMstrPk_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrIntfcDtlTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrIntfcStsCd_A);
                // MOD start 2016/04/04 CSA Defect#5822
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).intfcErrMsgTxt_A);
                // MOD end 2016/04/04 CSA Defect#5822
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrProcStsCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrProcStsDescTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNm_AC);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNm_AB);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).leaseCmpyCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNm_AL);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcContrBrCd_A);
                // add start 2016/06/30 CSA Defect#10661
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrAdminPsnCd_A);
                // add end 2016/06/30 CSA Defect#10661
                scrnMsg.addCheckItem(scrnMsg.A.no(i).tocCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).custPoNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).poDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).crCardCustRefNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).crCardExprYrMth_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrEstTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPgmMdseCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrThruDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgCycleCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcAllocByMachQtyFlg_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrAutoRnwTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).rnwPrcMethCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).befEndRnwDaysAot_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).rnwPrcUpRatio_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrUplftTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftPrcMethCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftPrcUpRatio_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrReadMethCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).basePrcDealAmt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrCloDay_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrBllgDay_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgThruDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgMtrLbCd_A);
                // START 2016/05/25 T.Tomita [QC#8898, MOD]
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgMtrLbCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrLbDescTxt_A);
                // END 2016/05/25 T.Tomita [QC#8898, MOD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).startMtrCnt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgRollOverRatio_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrCatgCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrStsCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xsChrgTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xsMtrCopyQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xsMtrAmtRate_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).addlChrgTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).addlChrgAplcPct_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).chrgLvlTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).addlChrgInvTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).printDtlFlg_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).basePrcTermDealAmtRate_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrClsCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).ctacPsnPk_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).ctacPsnNm_A);
                // START 2022/03/22 [QC#59683, MOD]
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).invSeptBaseUsgFlg_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsInvTgtrTpCd_AS);
                // END   2022/03/22 [QC#59683, MOD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrCloDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).contrDurnAot_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).pmtTermCashDiscCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcLineBizCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).bllgTmgTpCd_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrEdiCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrDescTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).baseChrgToLeaseCmpyFlg_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).usgChrgToLeaseCmpyFlg_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).intgMdseCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).capBwOrigQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).capColorOrigQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).capTotOrigQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).capBwRunQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).capColorRunQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).capTotRunQty_A);
                // START 2016/05/20 [QC#4061, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).manContrOvrdFlg_A);
                // END   2016/05/20 [QC#4061, ADD]
            }
        }
    }
    /**
     * <pre>
     * checkInput for table
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0810BMsg
     */
    public static final void checkInputForTable(EZDCommonHandler handler, NSAL0810BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * clear PopupData For ScrnMsg
     * @param scrnMsg NSAL0810BMsg
     */
    public static final void clearPopupDataForScrnMsg(NSAL0810BMsg scrnMsg) {
        scrnMsg.xxPopPrm_SE.clear();
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
    }
}
