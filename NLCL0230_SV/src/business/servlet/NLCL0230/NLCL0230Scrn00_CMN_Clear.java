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
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0230.common.NLCL0230CommonLogic;
import business.servlet.NLCL0230.constant.NLCL0230Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLCL0230Scrn00_CMN_Clear extends S21CommonHandler implements NLCL0230Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Clear screen informations.
        NLCL0230BMsg scrnMsg = (NLCL0230BMsg) bMsg;
        NLCL0230CommonLogic.clear(scrnMsg);

        // Set alternate rows back-ground color.
        NLCL0230CommonLogic.setRowsBGColor(scrnMsg);
    }
}
