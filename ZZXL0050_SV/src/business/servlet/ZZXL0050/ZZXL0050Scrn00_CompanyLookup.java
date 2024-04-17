/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZXL0050;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0050Scrn00_CompanyLookup extends S21CommonHandler implements ZZXL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

		//ZZXL0050CMsg bizMsg = new ZZXL0050CMsg();
		//bizMsg.setBusinessID("ZZXL0050");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;
		//ZZXL0050CMsg bizMsg  = (ZZXL0050CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = new Object[2];
        params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm");
        ((EZDBStringItem) params[0]).setValue("Global Company Code");
        params[1] = scrnMsg.glblCmpyCd;
        setArgForSubScreen(params);
	}

}
