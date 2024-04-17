/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0030;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZML0030.constant.ZZML0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0030_ZZSL1110 extends S21CommonHandler implements ZZML0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;

		//ZZML0030CMsg bizMsg = new ZZML0030CMsg();
		//bizMsg.setBusinessID("ZZML0030");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		//ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;
		//ZZML0030CMsg bizMsg  = (ZZML0030CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}
