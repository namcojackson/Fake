/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NYEL0050;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NYEL0050.constant.NYEL0050Constant;
import business.blap.NYEL0050.NYEL0050CMsg;
import business.servlet.NYEL0050.common.NYEL0050CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NYEL0050_INIT extends S21CommonHandler implements NYEL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NYEL0050BMsg scrnMsg = (NYEL0050BMsg) bMsg;

		NYEL0050CMsg bizMsg = new NYEL0050CMsg();
		bizMsg.setBusinessID("NYEL0050");
		bizMsg.setFunctionCode("10");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
		// Set up initial buttons
        scrnMsg.setFocusItem(scrnMsg.A.no(0).glblCmpyCd);
		NYEL0050CommonLogic.initCommonButton(this);
		
 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NYEL0050BMsg scrnMsg = (NYEL0050BMsg) bMsg;
		NYEL0050CMsg bizMsg  = (NYEL0050CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}
