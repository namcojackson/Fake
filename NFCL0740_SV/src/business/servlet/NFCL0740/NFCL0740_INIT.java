/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0740.NFCL0740CMsg;
import business.servlet.NFCL0740.common.NFCL0740CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Write-Off Request Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Create          N/A
 * 2016/11/21   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL0740_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2016/11/21 H.Ikeda [QC#12865,ADD]
        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL0740");
        // End   2016/11/21 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;

        NFCL0740CMsg bizMsg = new NFCL0740CMsg();
        bizMsg.setBusinessID("NFCL0740");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        NFCL0740CMsg bizMsg  = (NFCL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0740CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        scrnMsg.setFocusItem(scrnMsg.arAdjRsnCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;

        scrnMsg.arAdjRsnCd_H1.setNameForMessage("Adjust Reason");
        scrnMsg.arAdjTpCd_H1.setNameForMessage("Activity");
        scrnMsg.dealRmngBalGrsAmt_H1.setNameForMessage("From Remaining Amount");
        scrnMsg.dealRmngBalGrsAmt_H2.setNameForMessage("To Remaining Amount");

        scrnMsg.dealRmngBalGrsAmt_H1.setAppFracDigit(2);
        scrnMsg.dealRmngBalGrsAmt_H2.setAppFracDigit(2);
    }
}
