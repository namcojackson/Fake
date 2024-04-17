/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL0070.NLAL0070CMsg;
import business.servlet.NLAL0070.common.NLAL0070CommonLogic;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_NextWeek extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        NLAL0070CMsg bizMsg = new NLAL0070CMsg();
        bizMsg.setBusinessID("NLAL0070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
        NLAL0070CMsg bizMsg  = (NLAL0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        scrnMsg.xxListNum_LY.setValue(0);
        
        NLAL0070CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        NLAL0070CommonLogic.disableTableTextFieldForSummary(scrnMsg);
    }

}
