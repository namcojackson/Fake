/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0750;

import static business.servlet.NSAL0750.constant.NSAL0750Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0750.NSAL0750CMsg;
import business.servlet.NSAL0750.common.NSAL0750CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Update Invoicing Rule
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/14   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL0750Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0750BMsg scrnMsg = (NSAL0750BMsg) bMsg;

        NSAL0750CMsg bizMsg = new NSAL0750CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0750BMsg scrnMsg = (NSAL0750BMsg) bMsg;
        NSAL0750CMsg bizMsg = (NSAL0750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0750CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
