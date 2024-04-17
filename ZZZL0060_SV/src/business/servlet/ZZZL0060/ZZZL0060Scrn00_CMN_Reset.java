/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0060;


import parts.common.*;
import parts.servletcommon.*;
import parts.servletcommon.gui.*;
//import business.blap.ZZZL0060.ZZZL0060CMsg;

import business.blap.ZZZL0060.ZZZL0060CMsg;
import business.servlet.ZZZL0060.common.ZZZL0060CommonLogic;
import business.servlet.ZZZL0060.constant.ZZZL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0060Scrn00_CMN_Reset extends S21CommonHandler implements ZZZL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
        // Set Global Company Code
        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());
        // Initial Focus Control
        scrnMsg.setFocusItem(scrnMsg.batProcJobId);
        scrnMsg.glblCmpyCd.setInputProtected(true);
        scrnMsg.batProcJobId.setInputProtected(false);
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        
        ZZZL0060CommonLogic.initCommonButton(this);
	}
}
