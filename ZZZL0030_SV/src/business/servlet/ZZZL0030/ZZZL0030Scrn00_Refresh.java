/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0030;


import jofc2.model.Chart;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0030.ZZZL0030CMsg;
import business.servlet.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0030Scrn00_Refresh extends S21CommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
//        ZZZL0030CommonLogic.checkItem(scrnMsg);
//        ZZZL0030CommonLogic.correlativeTimeCheck(scrnMsg);
//        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;

		ZZZL0030CMsg bizMsg = new ZZZL0030CMsg();
		bizMsg.setBusinessID("ZZZL0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.setCustomAttribute("ChartDataBean", ctx.getAttribute("ChartDataBean"));

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
		ZZZL0030CMsg bizMsg  = (ZZZL0030CMsg) cMsg;
        Chart chart = (Chart)bizMsg.getCustomAttribute("ChartObj");
        ctx.setAttribute("ChartObj", chart);
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}
}
