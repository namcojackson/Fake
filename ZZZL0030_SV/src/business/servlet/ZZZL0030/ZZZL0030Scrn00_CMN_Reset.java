/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0030;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0030.ZZZL0030CMsg;
import business.servlet.ZZZL0030.common.ZZZL0030CommonLogic;
import business.servlet.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0030Scrn00_CMN_Reset extends S21CommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;

		ZZZL0030CMsg bizMsg = new ZZZL0030CMsg();
		bizMsg.setBusinessID("ZZZL0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
		ZZZL0030CMsg bizMsg  = (ZZZL0030CMsg) cMsg;
        
        String xxFromDt = scrnMsg.xxFromDt.getValue();
        String xxToDt = scrnMsg.xxToDt.getValue();
        String xxHrs_FS = scrnMsg.xxHrs_FS.getValue();
        String xxMn_FS = scrnMsg.xxMn_FS.getValue();
        String xxHrs_TS = scrnMsg.xxHrs_TS.getValue();
        String xxMn_TS = scrnMsg.xxMn_TS.getValue();

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // Clear items
        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());
        
        // Initial Focus Control
        scrnMsg.setFocusItem(scrnMsg.opsUsrId);
        // Set Pulldowns
        ZZZL0030CommonLogic.initCommonButton(this);
        ZZZL0030CommonLogic.initPullDowns(scrnMsg);
        
        scrnMsg.xxFromDt.setValue(xxFromDt);
        scrnMsg.xxToDt.setValue(xxToDt);
        scrnMsg.xxHrs_FS.setValue(xxHrs_FS);
        scrnMsg.xxMn_FS.setValue(xxMn_FS);
        scrnMsg.xxHrs_TS.setValue(xxHrs_TS);
        scrnMsg.xxMn_TS.setValue(xxMn_TS);

        ctx.setAttribute("ChartObj", null);
        ctx.setAttribute("ChartDataBean", null);
        
        scrnMsg.xxFromDt.setInputProtected(false);
        scrnMsg.xxToDt.setInputProtected(false);
        scrnMsg.xxHrs_FS.setInputProtected(false);
        scrnMsg.xxMn_FS.setInputProtected(false);
        scrnMsg.xxHrs_TS.setInputProtected(false);
        scrnMsg.xxMn_TS.setInputProtected(false);
        this.setButtonEnabled("Refresh", false);
        this.setButtonEnabled("View", false);
	}
}
