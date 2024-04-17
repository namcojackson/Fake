/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0760;

import static business.servlet.NFCL0760.constant.NFCL0760Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0760.NFCL0760CMsg;
import business.servlet.NFCL0760.common.NFCL0760CommonLogic;
import business.servlet.NFCL0760.constant.NFCL0760Constant.FUNC;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 *</pre>
 */
public class NFCL0760_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL0760BMsg scrnMsg = (NFCL0760BMsg) bMsg;
        NFCL0760CMsg bizMsg = new NFCL0760CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        Serializable arg = getArgForSubScreen();
        Object[] params = (Object[]) arg;

        if (params != null && params.length > PRM_ZERO && params[PRM_ZERO] instanceof EZDBBigDecimalItem) {
            setValue(bizMsg.arWrtOffRqstPk_H, ((EZDBBigDecimalItem) params[PRM_ZERO]));
        }
        if (params != null && params.length > PRM_ONE && params[PRM_ONE] instanceof EZDBStringItem) {
            setValue(bizMsg.wrtOffRqstGrpCd_H, ((EZDBStringItem) params[PRM_ONE]));
        }
        if (params != null && params.length > PRM_TWO && params[PRM_TWO] instanceof EZDBStringItem) {
            setValue(bizMsg.wrtOffRqstUsrId_H, ((EZDBStringItem) params[PRM_TWO]));
        }
        if (params != null && params.length > PRM_THREE && params[PRM_THREE] instanceof EZDBStringItem) {
            setValue(bizMsg.arWrtOffRqstTpCd_H, ((EZDBStringItem) params[PRM_THREE]));
        }
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        if (params != null && params.length > PRM_FOUR && params[PRM_FOUR] instanceof EZDBDateItem) {
            setValue(bizMsg.xxFromDt_P, ((EZDBDateItem) params[PRM_FOUR]));
        }
        if (params != null && params.length > PRM_FIVE && params[PRM_FIVE] instanceof EZDBDateItem) {
            setValue(bizMsg.xxThruDt_P, ((EZDBDateItem) params[PRM_FIVE]));
        }
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0760BMsg scrnMsg = (NFCL0760BMsg) bMsg;
        NFCL0760CMsg bizMsg = (NFCL0760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NFCL0760CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL0760BMsg scrnMsg = (NFCL0760BMsg) bMsg;
        scrnMsg.wrtOffRqstUsrId_H.setNameForMessage("User ID");
        scrnMsg.wrtOffRqstGrpCd_H.setNameForMessage("Write Off Request#");
        scrnMsg.xxTotAmt_H.setNameForMessage("Total Amount");
        scrnMsg.arAdjRsnDescTxt_H.setNameForMessage("Reason");
        scrnMsg.arAdjTpDescTxt_H.setNameForMessage("Activity");
        scrnMsg.arWrtOffNoteTxt_H.setNameForMessage("Note");
        scrnMsg.lowRmngBalAmt_H.setNameForMessage("Remaining Amount (From)");
        scrnMsg.highRmngBalAmt_H.setNameForMessage("Remaining Amount (To)");
        scrnMsg.lowInvNum_H.setNameForMessage("Invoice# (From)");
        scrnMsg.highInvNum_H.setNameForMessage("Invoice# (To)");
        scrnMsg.lowInvDueDt_H.setNameForMessage("Due Date (From)");
        scrnMsg.highInvDueDt_H.setNameForMessage("Due Date (To)");
        scrnMsg.lowDsAcctNum_H.setNameForMessage("Customer Number (From)");
        scrnMsg.highDsAcctNum_H.setNameForMessage("Customer Number (To)");
        scrnMsg.invTpDescTxt_H.setNameForMessage("Invoice Class");
        scrnMsg.inclConslInvFlg_H.setNameForMessage("Include Consolidated Invoice");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFCL0760_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.adjDt_A.setNameForMessage("Adjustment Date");
            abMsg.billToCustAcctCd_A.setNameForMessage("Cust Num");
            abMsg.dsAcctNm_A.setNameForMessage("Customer Name");
            abMsg.arTrxNum_A.setNameForMessage("Invoice#");
            abMsg.invTpDescTxt_A.setNameForMessage("Inv Cls");
            abMsg.dsInvTpDescTxt_A.setNameForMessage("Inv Type");
            abMsg.dealOrigGrsAmt_A.setNameForMessage("Invoice Amount");
            abMsg.invDueDt_A.setNameForMessage("Due Date");
            abMsg.dealApplyAdjRsvdAmt_A.setNameForMessage("WO Pdg AMT");
            abMsg.dealRmngBalGrsAmt_A.setNameForMessage("Rmng Bal");
            abMsg.arAdjNum_A.setNameForMessage("Adj Num");
            abMsg.dealArAdjAmt_A.setNameForMessage("Adj Amt");
            // START 2018/02/28 J.Kim [QC#21143,MOD]
            // abMsg.procStsDescTxt_A.setNameForMessage("Proc Sts");
            abMsg.arDsWfStsDescTxt_A.setNameForMessage("Wf Sts");
            // END 2018/02/28 J.Kim [QC#21143,MOD]
            abMsg.wrtOffErrMsgTxt_A.setNameForMessage("Error Message");
        }
    }

}
