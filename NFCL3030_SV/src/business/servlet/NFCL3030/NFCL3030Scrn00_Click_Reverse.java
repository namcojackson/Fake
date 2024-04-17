/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.common.EZDFieldErrorException;
//import business.blap.NFCL3030.NFCL3030CMsg;
//import business.servlet.NFCL3030.constant.NFCL3030Constant;

import business.blap.NFCL3030.NFCL3030CMsg;
import business.servlet.NFCL3030.common.NFCL3030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2022/08/04   CITS            D.Mamaril       Update          QC#60376
 *</pre>
 */
public class NFCL3030Scrn00_Click_Reverse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.voidDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptVoidRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptRvrsRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.voidGlDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptRvrsCmntTxt_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3030CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        scrnMsg.addCheckItem(scrnMsg.voidDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptVoidRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptRvrsRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.voidGlDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptRvrsCmntTxt_H);
        scrnMsg.putErrorScreen();

        // START 2022/08/04 D.Mamaril [QC#60376,ADD]
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2022/08/04 D.Mamaril [QC#60376,ADD]
    }
}
