/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0070;


import jofc2.model.Chart;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0070.ZZZL0070CMsg;
import business.servlet.ZZZL0070.common.ZZZL0070CommonLogic;
import business.servlet.ZZZL0070.constant.ZZZL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0070Scrn00_View extends S21CommonHandler implements ZZZL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) bMsg;
        ZZZL0070CommonLogic.checkItem(scrnMsg);
        ZZZL0070CommonLogic.correlativeTimeCheck(scrnMsg);
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) bMsg;

		ZZZL0070CMsg bizMsg = new ZZZL0070CMsg();
		bizMsg.setBusinessID("ZZZL0070");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) bMsg;
		ZZZL0070CMsg bizMsg  = (ZZZL0070CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        ctx.setAttribute("JobTmData", null);
        ctx.setAttribute("JobCntData", null);
        ctx.setAttribute("TableSizeData", null);
        ctx.setAttribute("TableCntData", null);
        scrnMsg.delFlg.setValue("Y");

        if (!"E".equals(bizMsg.getMessageKind())) {
            if (!bizMsg.getMessageCode().equals("ZZZM9005W")) {
                Chart chartA = (Chart)bizMsg.getCustomAttribute("JobTmData");
                Chart chartB = (Chart)bizMsg.getCustomAttribute("JobCntData");
                Chart chartC = (Chart)bizMsg.getCustomAttribute("TableSizeData");
                Chart chartD = (Chart)bizMsg.getCustomAttribute("TableCntData");
                ctx.setAttribute("JobTmData", chartA);
                ctx.setAttribute("JobCntData", chartB);
                ctx.setAttribute("TableSizeData", chartC);
                ctx.setAttribute("TableCntData", chartD);
            }
        }
        if (ctx.getAttribute("JobTmData") != null
                || ctx.getAttribute("JobCntData") != null
                || ctx.getAttribute("TableSizeData") != null
                || ctx.getAttribute("TableCntData") != null ) {
            scrnMsg.delFlg.setValue("N");
        }
	}
}
