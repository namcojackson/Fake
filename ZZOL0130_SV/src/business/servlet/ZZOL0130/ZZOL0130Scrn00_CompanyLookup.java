/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0130;


import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsgAccessor;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0130Scrn00_CompanyLookup extends S21CommonHandler implements ZZOL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

		//ZZOL0130CMsg bizMsg = new ZZOL0130CMsg();
		//bizMsg.setBusinessID("ZZOL0130");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
		//ZZOL0130CMsg bizMsg  = (ZZOL0130CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);

		Object[] params = new Object[2];							
        params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm");							
        ((EZDBStringItem) params[0]).setValue("Global Company Code");							
        params[1] = scrnMsg.glblCmpyCd;							
        setArgForSubScreen(params);							

	}

}
