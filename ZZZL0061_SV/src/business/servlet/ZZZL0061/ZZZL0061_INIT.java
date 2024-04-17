/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0061;


import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0061.common.ZZZL0061CommonLogic;
import business.servlet.ZZZL0061.constant.ZZZL0061Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0061_INIT extends S21INITCommonHandler implements ZZZL0061Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0061BMsg scrnMsg = (ZZZL0061BMsg) bMsg;
        
        Serializable arg = getArgForSubScreen();
        if ( arg != null) {
            if( arg instanceof Object[] ) {
               Object[] params = (Object[])arg;
               EZDBStringItem glblCmpyCd = (EZDBStringItem) params[0];
               scrnMsg.glblCmpyCd.setValue(glblCmpyCd.getValue());
               if (params.length >=3) {
                   scrnMsg.xxFlgNm.setValue("0071");
               }
            }
        }
        ZZZL0061CommonLogic.initCommonButton(this);
        scrnMsg.glblCmpyCd.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.batProcJobId);
	}
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        ZZZL0061BMsg scrnMsg = (ZZZL0061BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.batProcJobId.setNameForMessage("Job ID");
        scrnMsg.batProcPgmId.setNameForMessage("Program ID");
    }
}
