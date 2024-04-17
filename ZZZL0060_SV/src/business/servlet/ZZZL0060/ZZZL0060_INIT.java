/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0060;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0060.constant.ZZZL0060Constant;
import business.servlet.ZZZL0060.common.ZZZL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0060_INIT extends S21INITCommonHandler implements ZZZL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
        
	    //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;

		//ZZZL0060CMsg bizMsg = new ZZZL0060CMsg();
		//bizMsg.setBusinessID("ZZZL0060");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
		//ZZZL0060CMsg bizMsg  = (ZZZL0060CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // Set Global Company Code
        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());

        scrnMsg.setFocusItem(scrnMsg.batProcJobId);
        scrnMsg.glblCmpyCd.setInputProtected(true);
        
        ZZZL0060CommonLogic.initCommonButton(this);
	}
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        ZZZL0060BMsg scrnMsg = (ZZZL0060BMsg) bMsg;
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.batProcJobId.setNameForMessage("Job ID");
    }
}
