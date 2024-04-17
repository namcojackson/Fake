/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0071;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZZL0071.ZZZL0071CMsg;

import business.servlet.ZZZL0071.constant.ZZZL0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0071Scrn00_Search extends S21CommonHandler implements ZZZL0071Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;
		ZZZL0071CMsg bizMsg = new ZZZL0071CMsg();
		bizMsg.setBusinessID("ZZZL0071");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;
		ZZZL0071CMsg bizMsg  = (ZZZL0071CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}
