/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL4690;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL4690.constant.NMAL4690Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL4690Scrn00_CMN_Close extends S21CommonHandler implements NMAL4690Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;

		//NMAL4690CMsg bizMsg = new NMAL4690CMsg();
		//bizMsg.setBusinessID("NMAL4690");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		//NMAL4690BMsg scrnMsg = (NMAL4690BMsg) bMsg;
		//NMAL4690CMsg bizMsg  = (NMAL4690CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}