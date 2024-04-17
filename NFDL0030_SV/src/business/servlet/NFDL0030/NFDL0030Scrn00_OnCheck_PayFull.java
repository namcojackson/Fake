/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

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
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10203
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030Scrn00_OnCheck_PayFull extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//        NFDL0030CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (NFDL0030Constant.INV_NUM_ON_ACCOUNT.equals(scrnMsg.A.no(i).arTrxNum_A1.getValue())) {
                continue;
            }
            if (scrnMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(true);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dealNetRcptAmt_A1, scrnMsg.A.no(i).dealRmngBalGrsAmt_A1);
            } else {
                scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(false);
            }
        }
        // END 2023/03/10 S.Nakatani [QC#55645,MOD]
        // START 2016/08/26 K.Kojima [QC#10203,ADD]
        NFDL0030CommonLogic.calculationTotalAmountForModeInvoice(scrnMsg);
        // END 2016/08/26 K.Kojima [QC#10203,ADD]
    }
}
