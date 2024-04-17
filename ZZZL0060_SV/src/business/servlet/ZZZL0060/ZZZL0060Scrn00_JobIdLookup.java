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

import business.servlet.ZZZL0060.constant.ZZZL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0060Scrn00_JobIdLookup extends S21CommonHandler implements ZZZL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;

		//ZZZL0060CMsg bizMsg = new ZZZL0060CMsg();
		//bizMsg.setBusinessID("ZZZL0060");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
        Object[] params = new Object[2];
        params[0] = scrnMsg.glblCmpyCd;
        params[1] = scrnMsg.batProcJobId;
        setArgForSubScreen(params);


	}

}
