/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0071;


import java.io.Serializable;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZZL0071.common.ZZZL0071CommonLogic;
import business.servlet.ZZZL0071.constant.ZZZL0071Constant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0071_INIT extends S21INITCommonHandler implements ZZZL0071Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;

		//ZZZL0071CMsg bizMsg = new ZZZL0071CMsg();
		//bizMsg.setBusinessID("ZZZL0071");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if ( arg != null) {
            if( arg instanceof Object[] ) {
               Object[] params = (Object[])arg;
               EZDBStringItem glblCmpyCd = (EZDBStringItem) params[0];
               scrnMsg.glblCmpyCd.setValue(glblCmpyCd.getValue());
            }
        }
        ZZZL0071CommonLogic.initCommonButton(this);
        scrnMsg.glblCmpyCd.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.batProcJobId);


	}
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        ZZZL0071BMsg scrnMsg = (ZZZL0071BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.batProcJobId.setNameForMessage("Job ID");
        scrnMsg.batProcPgmId.setNameForMessage("Program ID");
    }

}
