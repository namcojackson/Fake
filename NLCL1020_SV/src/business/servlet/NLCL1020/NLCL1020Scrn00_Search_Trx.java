/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1020.NLCL1020CMsg;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 *</pre>
 */
public class NLCL1020Scrn00_Search_Trx extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.invtyOrdNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020CMsg bizMsg = null;
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;
        bizMsg = NLCL1020CommonLogic.setRequestData20(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;
        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            // Enable Search
            NLCL1020CommonLogic.enableSearch(this, scrnMsg, true);
            if (NLCL1020Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }
        NLCL1020CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NLCL1020CommonLogic.initialize(this, scrnMsg);

        NLCL1020CommonLogic.scrnItemControl_For_Submit_Or_Search(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.invtyOrdNum);
    }

}
