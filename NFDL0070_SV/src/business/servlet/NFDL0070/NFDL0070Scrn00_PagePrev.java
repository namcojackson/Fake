/**
 * <Pre>
 * 
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 11/16/2009   Fujitsu         FXS)K.Sakano    Create          DefID 1740
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 *</pre>
 */
package business.servlet.NFDL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0070.NFDL0070CMsg;
import business.servlet.NFDL0070.common.NFDL0070CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFDL0070Scrn00_PagePrev.
 */
public class NFDL0070Scrn00_PagePrev extends S21CommonHandler   {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070CMsg bizMsg = null;

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        NFDL0070CommonLogic.controlTablePrev_NFDL0070Scrn00_A(this, scrnMsg);

        bizMsg = NFDL0070CommonLogic.setRequestData_NFDL0070Scrn00_PagePrev(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        NFDL0070CMsg bizMsg = (NFDL0070CMsg) cMsg;

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0070CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
