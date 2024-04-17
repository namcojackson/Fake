/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7300;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7300.NMAL7300CMsg;
import business.servlet.NMAL7300.common.NMAL7300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/10   Fujitsu         Y.Kanefusa      Create          S21_NA#20249
 *</pre>
 */
public class NMAL7300_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        NMAL7300CommonLogic.setInputParam(scrnMsg, (Object[]) arg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        NMAL7300CMsg bizMsg = new NMAL7300CMsg();
        bizMsg.setBusinessID("NMAL7300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        NMAL7300CMsg bizMsg  = (NMAL7300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7300CommonLogic.initCmnBtnProp(this);

        NMAL7300CommonLogic.setHeaderProtect(scrnMsg);
        NMAL7300CommonLogic.setDetailProtect(scrnMsg);
        setAppFracDigit(scrnMsg);
        NMAL7300CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7300CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");

    }
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7300BMsg scrnMsg = (NMAL7300BMsg) bMsg;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B.setNameForMessage("Checkbox");
            scrnMsg.B.no(i).qtyDiscDtlQty_B.setNameForMessage("Qty");
            scrnMsg.B.no(i).prcRuleDtlTxt_B.setNameForMessage("Price Break Amt");
            scrnMsg.B.no(i).prcRuleDtlRate_B.setNameForMessage("Price Break Percent");
        }

    }
    private void setAppFracDigit(NMAL7300BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcRuleDtlRate_B.setAppFracDigit(2);
        }
    }

}
