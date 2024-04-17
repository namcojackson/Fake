/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0040;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZML0040.ZZML0040CMsg;
import business.servlet.ZZML0040.common.ZZML0040CommonLogic;
import business.servlet.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZML0040_INIT extends S21INITCommonHandler implements ZZML0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	    //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), businessId);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;

		ZZML0040CMsg bizMsg = new ZZML0040CMsg();
		bizMsg.setBusinessID("ZZML0040");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;
		ZZML0040CMsg bizMsg  = (ZZML0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZML0040CommonLogic.setNameForMessage(scrnMsg);
        ZZML0040CommonLogic.setButtonScrn00(this);
        setButtonEnabled(CMN_BTN7[0], scrnMsg.A.getValidCount() > 0);
        scrnMsg.setFocusItem( scrnMsg.glblCmpyCd );
//        setButtonEnabled(CMN_BTN9[0], true);
	}

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        //ZZML0030BMsg scrnMsg = (ZZML0030BMsg) bMsg;
        
    }

}
