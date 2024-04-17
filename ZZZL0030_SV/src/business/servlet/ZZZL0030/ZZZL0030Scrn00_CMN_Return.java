/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0030;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0030Scrn00_CMN_Return extends S21CommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;

		//ZZZL0030CMsg bizMsg = new ZZZL0030CMsg();
		//bizMsg.setBusinessID("ZZZL0030");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		//ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
		//ZZZL0030CMsg bizMsg  = (ZZZL0030CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}
}
