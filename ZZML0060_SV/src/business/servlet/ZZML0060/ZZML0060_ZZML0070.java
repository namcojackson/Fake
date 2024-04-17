/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0060;


import parts.common.*;
import parts.servletcommon.*;
import parts.servletcommon.gui.*;
//import business.blap.ZZML0060.ZZML0060CMsg;

import business.servlet.ZZML0060.constant.ZZML0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0060_ZZML0070 extends S21CommonHandler implements ZZML0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;

		//ZZML0060CMsg bizMsg = new ZZML0060CMsg();
		//bizMsg.setBusinessID("ZZML0060");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		//ZZML0060BMsg scrnMsg = (ZZML0060BMsg) bMsg;
		//ZZML0060CMsg bizMsg  = (ZZML0060CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}
