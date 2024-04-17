/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0032;


import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0032.common.ZZZL0032CommonLogic;
import business.servlet.ZZZL0032.constant.ZZZL0032Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0032_INIT extends S21INITCommonHandler implements ZZZL0032Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;
        
        Serializable arg = getArgForSubScreen();
        if ( arg != null) {
            if( arg instanceof Object[] ) {
               Object[] params = (Object[])arg;
               EZDBStringItem glblCmpyCd = (EZDBStringItem) params[0];
               scrnMsg.glblCmpyCd.setValue(glblCmpyCd.getValue());
            }
        }
        ZZZL0032CommonLogic.initCommonButton(this);
        scrnMsg.glblCmpyCd.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.bizId);
	}
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        ZZZL0032BMsg scrnMsg = (ZZZL0032BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.bizId.setNameForMessage("BusinessID");
        scrnMsg.scrAppId.setNameForMessage("Screen EventID");
    }
}
