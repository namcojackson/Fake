/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3080;

import static business.servlet.NFCL3080.constant.NFCL3080Constant.BIZ_ID;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.FUNC_CD_SRCH;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3080.NFCL3080CMsg;
import business.servlet.NFCL3080.common.NFCL3080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NFCL3080_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Fujita        Create          N/A
 * 2017/10/05   Fujitsu         H.Sugawara      Update          QC#19922
 *</pre>
 */
public class NFCL3080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3080BMsg scrnMsg = (NFCL3080BMsg) bMsg;
        NFCL3080CMsg bizMsg = new NFCL3080CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params != null && params.length == 1) {
                EZDBStringItem invNum = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNum, invNum);
            }
        } else {
            scrnMsg.invNum.clear();
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3080BMsg scrnMsg = (NFCL3080BMsg) bMsg;
        NFCL3080CMsg bizMsg = (NFCL3080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NFCL3080CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NFCL3080CommonLogic.initCmnBtnProp(this);
        NFCL3080CommonLogic.setGuiAttr(this, scrnMsg);

        scrnMsg.invNum.setInputProtected(true);

        scrnMsg.setFocusItem(scrnMsg.invNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL3080BMsg scrnMsg = (NFCL3080BMsg) bMsg;

        scrnMsg.invNum.setNameForMessage("Invoice Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFCL3080_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.invLineNum_A.setNameForMessage("Line");
            aBMsg.bllgPerFromDt_A.setNameForMessage("Billing From");
            aBMsg.bllgPerThruDt_A.setNameForMessage("Billing To");
            aBMsg.serNum_A.setNameForMessage("Serial Number");
            aBMsg.mtrLbDescTxt_A.setNameForMessage("Meter Type");
            aBMsg.prevTotCopyQty_A.setNameForMessage("Start Reading");
            aBMsg.totCopyQty_A.setNameForMessage("End Reading");
            aBMsg.testCopyQty_A.setNameForMessage("Test Copies");
            aBMsg.testCopyQty_B.setNameForMessage("Copies Made");
            aBMsg.contrMtrMultRate_A.setNameForMessage("Multiplier");
            aBMsg.copyInclQty_A.setNameForMessage("Allowance");
            aBMsg.bllgCopyQty_A.setNameForMessage("Billable Copies");
            aBMsg.xsMtrAmtRate_A.setNameForMessage("Rate");
            aBMsg.mtrChrgDealAmt_A.setNameForMessage("Amount");
            // Mod Start 2017/10/05 QC#19922
            //aBMsg.shipToCustCd_A.setNameForMessage("Location");
            aBMsg.shipToCustCd_A.setNameForMessage("Ship To Code");
            // Mod End 2017/10/05 QC#19922
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mtrChrgDealAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).mtrChrgFuncAmt_A.setAppFracDigit(2);
        }
    }
}
