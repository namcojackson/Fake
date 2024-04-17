/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0051;

import java.io.Serializable;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZOL0051.ZZOL0051CMsg;
import business.servlet.ZZOL0051.common.ZZOL0051CommonLogic;
import business.servlet.ZZOL0051.constant.ZZOL0051Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZOL0051_INIT extends S21INITCommonHandler implements ZZOL0051Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;
		//security violation check of this screen.
		checkBusinessAppGranted(getContextUserInfo().getUserId(), businessId);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if ( arg != null && arg instanceof Object[] ) {
            Object[] params = (Object[])arg;
            scrnMsg.glblCmpyCd.setValue( (String)params[0] );
            scrnMsg.upldCsvId.setValue( (String)params[1] );
        }
        scrnMsg.H.clear();
        scrnMsg.H.setValidCount(0);
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);
        scrnMsg.P.clear();
        scrnMsg.P.setValidCount(0);
        
		ZZOL0051CMsg bizMsg = new ZZOL0051CMsg();
		bizMsg.setBusinessID("ZZOL0051");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;
		ZZOL0051CMsg bizMsg  = (ZZOL0051CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        ZZOL0051CommonLogic.setNameForMessage(scrnMsg);
        ZZOL0051CommonLogic.setButtonScrn00(this);
        ZZOL0051CommonLogic.setScreenInit(this, scrnMsg, bizMsg);
	}

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        
    }

}
