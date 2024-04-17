/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.servlet.NFDL0030.common.NFDL0030CommonLogic;
import business.servlet.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/08/31   Hitachi         K.Kojima        Update          QC#10786
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030Scrn00_DeleteLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        int chkCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxChkBox_A1.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                chkCnt++;
                // START 2023/03/10 S.Nakatani [QC#55645,ADD]
                setInputProtectedInvoice(this, scrnMsg, i);
                if (NFDL0030Constant.INV_NUM_ON_ACCOUNT.equals(scrnMsg.A.no(i).arTrxNum_A1.getValue())) {
                    this.setButtonProperties("AddOnAcctLine", "AddOnAcctLine", "On Account", 1, null);
                }
                // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            }
        }
        if (chkCnt == 0) {
            scrnMsg.setMessageInfo("NFAM0075E");
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        NFDL0030CMsg bizMsg = new NFDL0030CMsg();
        bizMsg.setBusinessID("NFDL0030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg  = (NFDL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/08/31 K.Kojima [QC#10203,ADD]
        NFDL0030CommonLogic.calculationTotalAmountForModeInvoice(scrnMsg);
        // END 2016/08/31 K.Kojima [QC#10203,ADD]

        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setInputProtectedInvoice(this, scrnMsg, i);
        }
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        scrnMsg.putErrorScreen();
    }

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /**
     * setInputProtectedInvoice
     * @param S21CommonHandler handler
     * @param scrnMsg NFDL0030BMsg
     * @param int index of A
     */
    private void setInputProtectedInvoice(S21CommonHandler handler, NFDL0030BMsg scrnMsg, int i) {
        if (NFDL0030Constant.INV_NUM_ON_ACCOUNT.equals(scrnMsg.A.no(i).arTrxNum_A1.getValue())) {
            scrnMsg.A.no(i).arTrxNum_A1.setInputProtected(true);
            handler.setButtonEnabled("SearchInvoice", i, false);
            scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
            scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(false);
        } else {
            scrnMsg.A.no(i).arTrxNum_A1.setInputProtected(false);
            handler.setButtonEnabled("SearchInvoice", i, true);
            scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(false);
            scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(true);
        }
    }
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]
}
