/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0040;


import parts.common.*;
import parts.servletcommon.*;
import parts.servletcommon.gui.*;
//import business.blap.ZZZL0040.ZZZL0040CMsg;

import business.servlet.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0040Scrn00_CompanyLookup extends S21CommonHandler implements ZZZL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;

		//ZZZL0040CMsg bizMsg = new ZZZL0040CMsg();
		//bizMsg.setBusinessID("ZZZL0040");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
        
        Object[] params = new Object[2];
        params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm");
        ((EZDBStringItem) params[0]).setValue("Global Company Code");
        params[1] = scrnMsg.glblCmpyCd;
        setArgForSubScreen(params);
	}
}
