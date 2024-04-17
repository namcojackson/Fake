/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0061;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZZL0061.ZZZL0061CMsg;

import business.servlet.ZZZL0061.constant.ZZZL0061Constant;
import business.servlet.ZZZL0061.common.ZZZL0061CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0061Scrn00_PagePrev extends S21CommonHandler implements ZZZL0061Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0061BMsg scrnMsg = (ZZZL0061BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0061BMsg scrnMsg = (ZZZL0061BMsg) bMsg;
		ZZZL0061CMsg bizMsg = new ZZZL0061CMsg();
		bizMsg.setBusinessID("ZZZL0061");
		bizMsg.setFunctionCode("20");
        
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0061BMsg scrnMsg = (ZZZL0061BMsg) bMsg;
		ZZZL0061CMsg bizMsg  = (ZZZL0061CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZZZL0061CommonLogic.ControlButtonsDisplay(this, scrnMsg);

	}

}
