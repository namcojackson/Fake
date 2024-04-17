/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZXL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZXL0030.ZZXL0030CMsg;
import business.servlet.ZZXL0030.constant.ZZXL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0030Scrn00_CheckWeekDaysA extends S21CommonHandler implements ZZXL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		//ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;

		ZZXL0030CMsg bizMsg = new ZZXL0030CMsg();
		bizMsg.setBusinessID("ZZXL0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
		
 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
		ZZXL0030CMsg bizMsg  = (ZZXL0030CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}
}