/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7050.NMAL7050CMsg;
import business.servlet.NMAL7050.common.NMAL7050CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NMAL7050");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;

        NMAL7050CMsg bizMsg = new NMAL7050CMsg();
        bizMsg.setBusinessID("NMAL7050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;
        NMAL7050CMsg bizMsg = (NMAL7050CMsg) cMsg;

        NMAL7050CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;
        scrnMsg.prcMtrPkgNm.setNameForMessage("Meter Package Name(*)");
        scrnMsg.xxDsMultMsgDplyTxt.setNameForMessage("Service Model Name(*)");
        scrnMsg.mtrLbDescTxt_BG.setNameForMessage("Contracts Billing Counter Name(*)");
        scrnMsg.mtrLbDescTxt_PH.setNameForMessage("Contracts Hard Counter Name(*)");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        // page jump common setting
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21SequentialSearchPagenationScrnSupport.getCurrentPageFieldName());
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlNm_A1.setNameForMessage("Service Model Name");
            scrnMsg.A.no(i).prcMtrPkgNm_A1.setNameForMessage("Meter Package Display Name");
            scrnMsg.A.no(i).prcMtrPkgDescTxt_A1.setNameForMessage("Meter Package Description");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("End Date");
        }
    }
}
