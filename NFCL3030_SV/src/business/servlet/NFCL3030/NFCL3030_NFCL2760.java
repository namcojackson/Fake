/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3030.NFCL3030CMsg;
import business.servlet.NFCL3030.common.NFCL3030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2021/10/20   CITS            G.Delgado       Update          QC#59241
 *</pre>
 */
public class NFCL3030_NFCL2760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START G.Delgado 2021/10/20 [QC#59241,ADD]
        NFCL3030CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        // END G.Delgado 2021/10/20 [QC#59241,ADD]
    }
}