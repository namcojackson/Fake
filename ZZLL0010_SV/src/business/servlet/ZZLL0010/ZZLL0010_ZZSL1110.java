/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZLL0010;


import java.io.Serializable;

import parts.common.*;
import parts.servletcommon.*;
//import business.blap.ZZLL0010.ZZLL0010CMsg;

import business.servlet.ZZLL0010.constant.ZZLL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZLL0010_ZZSL1110 extends S21CommonHandler implements ZZLL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;

		//ZZLL0010CMsg bizMsg = new ZZLL0010CMsg();
		//bizMsg.setBusinessID("ZZLL0010");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);

        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;
                String userCompanyCode = (String) params[1];
                scrnMsg.glblCmpyCd.setValue(userCompanyCode);
            }
        }


	}

}
