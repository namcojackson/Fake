/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/05   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
package business.servlet.NLCL0230;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0230.NLCL0230CMsg;
import business.servlet.NLCL0230.common.NLCL0230CommonLogic;
import business.servlet.NLCL0230.constant.NLCL0230Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLCL0230Scrn00_Search_Inventory extends S21CommonHandler implements NLCL0230Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Execute search for Inventory.
        NLCL0230BMsg scrnMsg = (NLCL0230BMsg) bMsg;
        NLCL0230CMsg bizMsg = new NLCL0230CMsg();
        bizMsg.setBusinessID("NLCL0230");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Copy bizMsg to scrnMsg.
        NLCL0230BMsg scrnMsg = (NLCL0230BMsg) bMsg;
        NLCL0230CMsg bizMsg  = (NLCL0230CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set alternate rows back-ground color.
        NLCL0230CommonLogic.setRowsBGColor(scrnMsg);

        // Focus the Inventory Location Code.
        scrnMsg.setFocusItem(scrnMsg.invtyLocCd);
    }
}
