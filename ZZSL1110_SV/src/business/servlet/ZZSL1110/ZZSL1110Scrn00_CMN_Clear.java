/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZSL1110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZSL1110.constant.ZZSL1110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZSL1110Scrn00_CMN_Clear extends S21CommonHandler implements ZZSL1110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZSL1110BMsg scrnMsg = (ZZSL1110BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZSL1110BMsg scrnMsg = (ZZSL1110BMsg) bMsg;

		//ZZSL1110CMsg bizMsg = new ZZSL1110CMsg();
		//bizMsg.setBusinessID("ZZSL1110");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZSL1110BMsg scrnMsg = (ZZSL1110BMsg) bMsg;
		//ZZSL1110CMsg bizMsg  = (ZZSL1110CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);
         scrnMsg.glblCmpyCd.clear();
         scrnMsg.glblCmpyNm.clear();
         
         scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);

	}

}
