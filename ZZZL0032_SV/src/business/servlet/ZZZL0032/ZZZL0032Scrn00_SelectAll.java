/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0032;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0032.ZZZL0032CMsg;
import business.servlet.ZZZL0032.constant.ZZZL0032Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0032Scrn00_SelectAll extends S21CommonHandler implements ZZZL0032Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;

		ZZZL0032CMsg bizMsg = new ZZZL0032CMsg();
		bizMsg.setBusinessID("ZZZL0032");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;
        ZZZL0032CMsg bizMsg  = (ZZZL0032CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}
}
