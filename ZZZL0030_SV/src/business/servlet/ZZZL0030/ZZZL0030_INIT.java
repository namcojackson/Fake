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

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0030_INIT extends S21INITCommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//    security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
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
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set Global Company Code
//        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());
        
        // Initial Focus Control
        scrnMsg.setFocusItem(scrnMsg.opsUsrId);
                
        // Set Pulldowns
        ZZZL0030CommonLogic.initCommonButton(this);
        ZZZL0030CommonLogic.initPullDowns(scrnMsg);
        
        // Set following contents to be not available.(ver.1.0)
        this.setButtonEnabled("Refresh", false);
        this.setButtonEnabled("View", false);
        this.setButtonEnabled("DelBiz", false);
        
        ctx.setAttribute("ChartObj", null);
        ctx.setAttribute("ChartDataBean", null);
	}
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.opsUsrId.setNameForMessage("UserID");
        scrnMsg.jvmNm_S.setNameForMessage("JVM name");
        scrnMsg.xxYAxle.setNameForMessage("Y-axle");
        scrnMsg.xxFromDt.setNameForMessage("From Date");
        scrnMsg.xxHrs_FS.setNameForMessage("From Hour");
        scrnMsg.xxMn_FS.setNameForMessage("From Minutes");
        scrnMsg.xxToDt.setNameForMessage("To Date");
        scrnMsg.xxHrs_TS.setNameForMessage("To Hour");
        scrnMsg.xxMn_TS.setNameForMessage("To Minutes");
    }
}
