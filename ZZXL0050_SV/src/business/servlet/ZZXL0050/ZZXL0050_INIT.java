/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZXL0050;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZXL0050.ZZXL0050CMsg;
import business.servlet.ZZXL0050.common.ZZXL0050CommonLogic;
import business.servlet.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZXL0050_INIT extends S21INITCommonHandler implements ZZXL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), businessId);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

		ZZXL0050CMsg bizMsg = new ZZXL0050CMsg();
		bizMsg.setBusinessID("ZZXL0050");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;
		ZZXL0050CMsg bizMsg  = (ZZXL0050CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.glblCmpyCd.setNameForMessage("Global Company CD");
        scrnMsg.dtProcCd.setNameForMessage(  "Date Type");
        scrnMsg.dtMgtPgmId.setNameForMessage("Program ID");
        
        ZZXL0050CommonLogic.setButtonScrn00(this);
        
        scrnMsg.setFocusItem( scrnMsg.glblCmpyCd );

	}

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        
    }

}
