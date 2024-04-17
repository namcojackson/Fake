/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLCL0280Scrn00_Select_TrxTp Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/06   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280Scrn00_Select_MdseItemRelnTp extends S21CommonHandler {

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

        // Clear item when there is no selection.
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseItemRelnTpCd_PS)) {
            scrnMsg.relnMdseCd_H1.clear();
        }

        // Screen Protect Ctrl
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);
    }
}
