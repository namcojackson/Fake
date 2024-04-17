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
import business.blap.ZZZL0030.common.ZZZL0030ChartBean;
import business.servlet.ZZZL0030.common.ZZZL0030CommonLogic;
import business.servlet.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0030Scrn00_View extends S21CommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
        ZZZL0030CommonLogic.checkItem(scrnMsg);
        ZZZL0030CommonLogic.correlativeTimeCheck(scrnMsg);
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
       
        ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
        
        ZZZL0030CMsg bizMsg = new ZZZL0030CMsg();
        bizMsg.setBusinessID("ZZZL0030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        ZZZL0030ChartBean bean = (ZZZL0030ChartBean)ctx.getAttribute("ChartDataBean");
        if (bean != null) {
            if (!bean.getFromDate().equals(bizMsg.bizStartTs.getValue())
                    || !bean.getToDate().equals(bizMsg.bizEndTs.getValue())) {
                bean = null;
            }
        }
        bizMsg.setCustomAttribute("ChartDataBean", bean);
        
        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
        ZZZL0030CMsg bizMsg  = (ZZZL0030CMsg) cMsg;
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (!"E".equals(bizMsg.getMessageKind())) {
            if (!bizMsg.getMessageCode().equals("ZZZM9005W")) {
                Chart chart = (Chart)bizMsg.getCustomAttribute("ChartObj");
                ZZZL0030ChartBean chartDataBean = (ZZZL0030ChartBean)bizMsg.getCustomAttribute("ChartDataBean");
                ctx.setAttribute("ChartObj", chart);
                ctx.setAttribute("ChartDataBean", chartDataBean);
                this.setButtonEnabled("Refresh", true);
            }
        }
        if (ctx.getAttribute("ChartObj") != null) {
            scrnMsg.delFlg.setValue("N");
        }
	}
}
