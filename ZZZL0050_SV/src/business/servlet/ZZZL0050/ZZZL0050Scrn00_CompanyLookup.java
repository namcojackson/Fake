/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0050;


import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsgAccessor;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0050.constant.ZZZL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0050Scrn00_CompanyLookup extends S21CommonHandler implements ZZZL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0050BMsg scrnMsg = (ZZZL0050BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0050BMsg scrnMsg = (ZZZL0050BMsg) bMsg;

		//ZZZL0050CMsg bizMsg = new ZZZL0050CMsg();
		//bizMsg.setBusinessID("ZZZL0050");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0050BMsg scrnMsg = (ZZZL0050BMsg) bMsg;
        Object[] params = new Object[2];
        params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm");
        ((EZDBStringItem) params[0]).setValue("Global Company Code");
        params[1] = scrnMsg.glblCmpyCd;
        setArgForSubScreen(params);
	}
}
