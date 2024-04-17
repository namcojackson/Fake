/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL0510.NFCL0510CMsg;
//import business.servlet.NFCL0510.constant.NFCL0510Constant;

import business.blap.NFCL0510.NFCL0510CMsg;
import business.servlet.NFCL0510.common.NFCL0510CommonLogic;
import business.servlet.NFCL0510.constant.NFCL0510Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 12/21/2016   Fujitsu         T.Murai         Update          QC#16710
 *</pre>
 */
public class NFCL0510Scrn00_CMN_Clear extends S21CommonHandler implements NFCL0510Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;

        NFCL0510CMsg bizMsg = new NFCL0510CMsg();
        bizMsg.setBusinessID("NFCL0510");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;
        NFCL0510CMsg bizMsg  = (NFCL0510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/12/22 [QC#16710,ADD]
        NFCL0510CommonLogic.setProtected(this, scrnMsg, getUserProfileService());
        // END   2016/12/22 [QC#16710,ADD]
    }
}
