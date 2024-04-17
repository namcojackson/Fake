/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0040;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0040.ZZZL0040CMsg;
import business.servlet.ZZZL0040.common.ZZZL0040CommonLogic;
import business.servlet.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0040Scrn00_View extends S21CommonHandler implements ZZZL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
        ZZZL0040CommonLogic.checkItem(scrnMsg);
        ZZZL0040CommonLogic.correlativeTimeCheck(scrnMsg);
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;

		ZZZL0040CMsg bizMsg = new ZZZL0040CMsg();
		bizMsg.setBusinessID("ZZZL0040");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
		ZZZL0040CMsg bizMsg  = (ZZZL0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        ZZZL0040CommonLogic.changeTableColorByRow(scrnMsg);
	}
}
