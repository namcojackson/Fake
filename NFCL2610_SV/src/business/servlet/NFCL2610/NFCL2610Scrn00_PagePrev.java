/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.servlet.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 *</pre>
 */
public class NFCL2610Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CMsg bizMsg = NFCL2610CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CMsg bizMsg = (NFCL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL2610CommonLogic.setFocusRule(scrnMsg);
        // START 2021/09/09 G.Delgado [QC#58793, ADD]
        NFCL2610CommonLogic.setEnabledRowsTblA(this, scrnMsg);
        // END 2021/09/09 G.Delgado [QC#58793, ADD]
    }
}
