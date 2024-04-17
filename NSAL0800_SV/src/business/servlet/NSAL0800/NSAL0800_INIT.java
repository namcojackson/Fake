/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0800.NSAL0800CMsg;
import business.servlet.NSAL0800.common.NSAL0800CommonLogic;

import static business.servlet.NSAL0800.constant.NSAL0800Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2018/05/31   CITS            M.Naito         Update          QC#22887
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0800_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;

        NSAL0800CMsg bizMsg = new NSAL0800CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;
        NSAL0800CMsg bizMsg = (NSAL0800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0800CommonLogic.initControlCommonButton(this);
        // START 2018/05/31 M.Naito [QC#22887, ADD]
        NSAL0800CommonLogic.controlScreenFields(scrnMsg);
        // END 2018/05/31 M.Naito [QC#22887, ADD]
        scrnMsg.dsAcctNm_A1.setInputProtected(true);
        if (NSAL0800CommonLogic.hasUpdateFuncId()) {
            NSAL0800CommonLogic.controlCommonButton(this, scrnMsg, true);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;

        // QC#18799 ADD and UPD START
        scrnMsg.contrIntfcSrcTpCd_HS.setNameForMessage("Source Type");
        scrnMsg.dsContrClsCd_HS.setNameForMessage("Category");
        scrnMsg.svcLineBizCd_HS.setNameForMessage("Line Of Business");
        scrnMsg.effFromDt_H1.setNameForMessage("Effect Start Date");
        scrnMsg.enblFlg_H1.setNameForMessage("Enable Flag");
        scrnMsg.leaseCmpyCd_A1.setNameForMessage("Default Lease Company");
        scrnMsg.dsAcctNm_A1.setNameForMessage("Default Lease Company Name");
        scrnMsg.mtrReadMethCd_AS.setNameForMessage("Mtr Method Default");
        scrnMsg.dsContrSlsCrTpCd_AS.setNameForMessage("Sales Credit");
        scrnMsg.mtrEstTpCd_ES.setNameForMessage("Auto Estimation");
        scrnMsg.bllgCycleCd_E2.setNameForMessage("Base");
        // START 2022/03/22 [QC#59683, MOD]
//        scrnMsg.invSeptBaseUsgFlg_E1.setNameForMessage("Bill Base & Usage Together");
        scrnMsg.dsInvTgtrTpCd_E1.setNameForMessage("Invoicing Option");
        // END   2022/03/22 [QC#59683, MOD]
        scrnMsg.bllgCycleCd_E4.setNameForMessage("Overage");
        scrnMsg.prcAllocByMachQtyFlg_E1.setNameForMessage("Allocate across All Machines");
        scrnMsg.printDtlFlg_E1.setNameForMessage("Print on Invoice");
        scrnMsg.invSeptBaseUsgMstrFlg_E1.setNameForMessage("Use Master Setting");
        scrnMsg.contrAutoRnwTpCd_BS.setNameForMessage("Type");
        scrnMsg.befEndRnwDaysAot_B1.setNameForMessage("#Days Before");
        scrnMsg.rnwPrcMethCd_BS.setNameForMessage("Method");
        scrnMsg.basePrcUpRatio_B1.setNameForMessage("%Base");
        scrnMsg.mtrPrcUpRatio_B1.setNameForMessage("%Overage");
        scrnMsg.rnwExtMthAot_B1.setNameForMessage("Extended Month");
        scrnMsg.contrUplftTpCd_FS.setNameForMessage("Type");
        scrnMsg.befEndUplftDaysAot_F1.setNameForMessage("#Days Before");
        scrnMsg.uplftPrcMethCd_FS.setNameForMessage("Method");
        scrnMsg.uplftBasePrcUpRatio_F1.setNameForMessage("%Base");
        scrnMsg.uplftMtrPrcUpRatio_F1.setNameForMessage("%Overage");
        scrnMsg.startReadVldTpCd_CS.setNameForMessage("Start Read Validation");
        scrnMsg.mtrReadWinDaysAot_C1.setNameForMessage("Reading Window Days");
        scrnMsg.istlCallMtrReadUseFlg_C1.setNameForMessage("Use Install Call Meter Read");
        scrnMsg.bllgRollOverRatio_C1.setNameForMessage("Counter Rollover %");
        scrnMsg.mtrReadWinMlyDaysAot_C1.setNameForMessage("for Monthly");
        scrnMsg.mtrReadWinOthDaysAot_C1.setNameForMessage("for Other Frequency");
        scrnMsg.bllgLimitAmt_C1.setNameForMessage("Preview Amount");
        scrnMsg.bllgThruTpCd_GS.setNameForMessage("Mark As Billed Schedule end date Prior To");
        scrnMsg.bllgThruDt_G1.setNameForMessage("Specific Date");
        scrnMsg.contrCloDay_GS.setNameForMessage("Period End Date Ctrl");
        scrnMsg.contrBllgDay_GS.setNameForMessage("Invoice Date Ctrl");
        scrnMsg.bllgTmgTpCd_GS.setNameForMessage("Base Billing Timing");
        scrnMsg.allwDataCrctFlg_D1.setNameForMessage("Allow Data Correction");
        scrnMsg.stubPrepFromTpCd_IS.setNameForMessage("Stub Prevention For Start");
        scrnMsg.stubPrepThruTpCd_IS.setNameForMessage("Stub Prevention For End");
        scrnMsg.autoEffFleetFlg_I1.setNameForMessage("Auto Derive Effectivity For Add To Fleet");
        scrnMsg.autoEffAggrFlg_I1.setNameForMessage("Auto Derive Effectivity For Add To Aggregate");
        // QC#18799 ADD and UPD END
    }
}
