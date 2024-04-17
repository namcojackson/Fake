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

public class ZZZL0030Scrn00_DelBiz extends S21CommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
        
        // checkbox check
        boolean checkboxAvaFlag = false;
        for (int k = 0; k < scrnMsg.A.getValidCount(); k++) {
            if (!scrnMsg.A.no(k).xxChkBox_A.isClear()) {
                checkboxAvaFlag = true;
                break;
            }
        }
        if (!checkboxAvaFlag) {
            for (int n = 0; n < scrnMsg.A.getValidCount(); n++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(n).xxChkBox_A);
                scrnMsg.A.no(n).xxChkBox_A.setErrorInfo(1, "ZZZM9007E", new String[]{"CHECK BOX"});
            }
        }
        scrnMsg.putErrorScreen();
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
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        Chart chart = (Chart)bizMsg.getCustomAttribute("ChartObj");
        ZZZL0030ChartBean chartDataBean = (ZZZL0030ChartBean)bizMsg.getCustomAttribute("ChartDataBean");
        ctx.setAttribute("ChartObj", chart);
        ctx.setAttribute("ChartDataBean", chartDataBean);
        
        if (scrnMsg.A.getValidCount() == 0) {
//            scrnMsg.xxFromDt.setInputProtected(false);
//            scrnMsg.xxToDt.setInputProtected(false);
//            scrnMsg.xxHrs_FS.setInputProtected(false);
//            scrnMsg.xxMn_FS.setInputProtected(false);
//            scrnMsg.xxHrs_TS.setInputProtected(false);
//            scrnMsg.xxMn_TS.setInputProtected(false);
            this.setButtonEnabled("DelBiz", false);
            this.setButtonEnabled("View", false);
            this.setButtonEnabled("Refresh", false);
            ZZZL0030CommonLogic.initPullDowns(scrnMsg);
        }
	}
}
