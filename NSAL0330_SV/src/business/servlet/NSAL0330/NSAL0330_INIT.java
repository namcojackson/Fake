/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0330;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0330.NSAL0330CMsg;
import business.servlet.NSAL0330.common.NSAL0330CommonLogic;
import business.servlet.NSAL0330.constant.NSAL0330Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/10/25   Hitachi         Y.Takeno        Update          QC#15518
 *</pre>
 */
public class NSAL0330_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0330Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            // START 2015/10/21 T.Tomita [N/A, MOD]
            if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_H1, param00);
            }

            if (params.length > 1 && params[1] != null && params[1] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPrcEffPk_H1, param01);
            }

            if (params.length > 2 && params[2] != null && params[2] instanceof EZDBStringItem) {
                EZDBStringItem param02 = (EZDBStringItem) params[2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_H1, param02);
            }
            // END 2015/10/21 T.Tomita [N/A, MOD]
        }

        NSAL0330CMsg bizMsg = new NSAL0330CMsg();
        bizMsg.setBusinessID(NSAL0330Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;
        NSAL0330CMsg bizMsg = (NSAL0330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0330CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.baseBllgTmgCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;

        // Header
        scrnMsg.dsContrNum_H1.setNameForMessage("Contract#");
        scrnMsg.serNum_H1.setNameForMessage("Serial#");
        // START 2015/10/21 T.Tomita [N/A, DEL]
//        scrnMsg.t_MdlNm_H1.setNameForMessage("Serial#");
//        scrnMsg.baseBillToCustCd_H1.setNameForMessage("Bill To Customer");
        // END 2015/10/21 T.Tomita [N/A, DEL]
        scrnMsg.contrEffFromDt_H1.setNameForMessage("Effective Start Date");
        scrnMsg.contrEffThruDt_H1.setNameForMessage("Effective Thru Date");
        scrnMsg.baseBllgTmgCd_H1.setNameForMessage("Billing Timing");
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        // START 2016/10/25 [QC#15518, MOD]
//        scrnMsg.baseDplyPerEndDay_H1.setNameForMessage("Closing Day");
        scrnMsg.baseDplyPerEndDay_H1.setNameForMessage("Period End Date");
        // END 2016/10/25 [QC#15518, MOD]
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

        scrnMsg.baseBllgLastBllgDt_H1.setNameForMessage("Billed Upto");
        scrnMsg.invTotAmt_H1.setNameForMessage("Term Amount");
        // START 2016/10/25 [QC#15518, MOD]
//        scrnMsg.contrBllgDay_H1.setNameForMessage("Billing Day");
        scrnMsg.contrBllgDay_H1.setNameForMessage("Invoice Date");
        // END 2016/10/25 [QC#15518, MOD]

        scrnMsg.svcMemoRsnCd_F3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_F1.setNameForMessage("Comment");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // START 2015/10/21 T.Tomita [N/A, MOD]
            scrnMsg.A.no(i).dsContrBllgSchdSqNum_A1.setNameForMessage("Seq No");
            scrnMsg.A.no(i).dsContrPrcEffSqNum_A1.setNameForMessage("PE Seq#");
            scrnMsg.A.no(i).perSchdNum_A1.setNameForMessage("Periods");
            scrnMsg.A.no(i).perBllgCycleCd_A1.setNameForMessage("Sched.UOM");
            scrnMsg.A.no(i).bllgSchdFromDt_A1.setNameForMessage("Sched.From Date");
            scrnMsg.A.no(i).bllgSchdThruDt_A1.setNameForMessage("Sched.Thru Date");
//            scrnMsg.A.no(i).bllgCycleCd_A3.setNameForMessage("Billing Cycle");
            scrnMsg.A.no(i).bllgCycleDescTxt_A1.setNameForMessage("Billing Cycle");
            scrnMsg.A.no(i).basePrcDealAmt_A1.setNameForMessage("Amount");
            scrnMsg.A.no(i).basePrcDealAdjAmt_A1.setNameForMessage("Rounding Adj.");
            scrnMsg.A.no(i).baseSubTotPrcDealAmt_A1.setNameForMessage("Total Amount");
            scrnMsg.A.no(i).ccyCd_A1.setNameForMessage("Currency");
            // END 2015/10/21 T.Tomita [N/A, MOD]
        }

    }
}
