/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.servlet.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/10   Hitachi         S.Nakatani      Create          QC#55645
 *</pre>
 */
public class NFDL0030Scrn00_AddOnAcctLine extends S21CommonHandler {

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
        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int i = scrnMsg.A.getValidCount() - 1;
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).arTrxNum_A1, NFDL0030Constant.INV_NUM_ON_ACCOUNT);
        scrnMsg.A.no(i).arTrxNum_A1.setInputProtected(true);
        this.setButtonEnabled("SearchInvoice", i, false);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dealRmngBalGrsAmt_A1, BigDecimal.ZERO);
        scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
        scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(false);

        this.setButtonProperties("AddOnAcctLine", "AddOnAcctLine", "On Account", 0, null);

        scrnMsg.putErrorScreen();
    }
}
