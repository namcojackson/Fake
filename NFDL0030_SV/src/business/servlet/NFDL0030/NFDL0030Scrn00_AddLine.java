/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.servlet.NFDL0030.common.NFDL0030CommonLogic;
import business.servlet.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/08/31   Hitachi         K.Kojima        Update          QC#10203
 * 2019/03/04   Hitachi         Y.Takeno        Update          QC#30607
 *</pre>
 */
public class NFDL0030Scrn00_AddLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
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

        // START 2019/03/04 [QC#30607, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (!NFDL0030Constant.INV_NUM_ON_ACCOUNT.equals(scrnMsg.A.no(i).arTrxNum_A1.getValue())) {
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
                if (scrnMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                    scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(true);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dealNetRcptAmt_A1, scrnMsg.A.no(i).dealRmngBalGrsAmt_A1);
                } else {
                    scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(false);
                }
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        }
        // END   2019/03/04 [QC#30607, ADD]

        // START 2016/08/31 K.Kojima [QC#10203,ADD]
        NFDL0030CommonLogic.calculationTotalAmountForModeInvoice(scrnMsg);
        // END 2016/08/31 K.Kojima [QC#10203,ADD]

        scrnMsg.putErrorScreen();
    }
}
