/**
 * <Pre>
 * 
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NFDL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0070.NFDL0070CMsg;
import business.servlet.NFDL0070.common.NFDL0070CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NFDL0070Scrn00_CMN_Return.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NFDL0070Scrn00_CMN_Return extends S21CommonHandler  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070CMsg bizMsg = null;

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        NFDL0070CommonLogic.initialize(this, scrnMsg);

    }
}
