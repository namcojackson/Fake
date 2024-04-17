/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import static business.servlet.NSAL0730.constant.NSAL0730Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0730.NSAL0730CMsg;
import business.servlet.NSAL0730.common.NSAL0730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Update PO Information
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/14   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL0730Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        NSAL0730CMsg bizMsg = new NSAL0730CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;
        NSAL0730CMsg bizMsg = (NSAL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0730CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
