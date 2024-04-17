/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Return Open_JrnlEntrySearch Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280_NFAL0120 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Screen Protect Ctrl
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);

    }
}