/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;


import parts.common.*;
import parts.servletcommon.*;
import parts.servletcommon.gui.*;
//import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0010Scrn00_CMN_Return extends S21CommonHandler implements ZZOL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;

		//ZZOL0010CMsg bizMsg = new ZZOL0010CMsg();
		//bizMsg.setBusinessID("ZZOL0010");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		//ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
		//ZZOL0010CMsg bizMsg  = (ZZOL0010CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}
