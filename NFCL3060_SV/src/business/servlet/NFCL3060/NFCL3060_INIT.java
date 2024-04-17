/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3060;

import static business.servlet.NFCL3060.constant.NFCL3060Constant.BIZ_ID;
import static business.servlet.NFCL3060.constant.NFCL3060Constant.FUNC_CD_SRCH;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3060.NFCL3060CMsg;
import business.servlet.NFCL3060.common.NFCL3060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NFCL3060_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCL3060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

       checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;
        NFCL3060CMsg bizMsg = new NFCL3060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params != null && params.length == 1) {
                EZDBStringItem artrxNum = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxNum, artrxNum);
            }
        } else {
            scrnMsg.arTrxNum.clear();
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;
        NFCL3060CMsg bizMsg = (NFCL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NFCL3060CommonLogic.initCmnBtnProp(this);

        scrnMsg.arTrxNum.setInputProtected(true);
        scrnMsg.xxDplyByItemNm.setInputProtected(true);

        scrnMsg.xxRadioBtn.setValue(0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;

        scrnMsg.arTrxNum.setNameForMessage("Invoice Number");
        scrnMsg.xxDplyByItemNm.setNameForMessage("Payment Term");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFCL3060_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.arTrxNum_A.setNameForMessage("Invoice Number");
            aBMsg.arTrxNum_B.setNameForMessage("Installment Number");
            aBMsg.arTrxTpDescTxt_A.setNameForMessage("Class");
            aBMsg.invDueDt_A.setNameForMessage("Due Date");
            aBMsg.dealOrigGrsAmt_A.setNameForMessage("Original Amount");
            aBMsg.dealRmngBalGrsAmt_A.setNameForMessage("Balance Due");
            aBMsg.arCashApplyStsDescTxt_A.setNameForMessage("Status");
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setAppFracDigit(2);
        }
    }
}
