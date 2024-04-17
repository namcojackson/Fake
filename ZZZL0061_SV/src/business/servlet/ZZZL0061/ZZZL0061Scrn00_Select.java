/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0061;


import java.io.Serializable;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZZL0061.constant.ZZZL0061Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0061Scrn00_Select extends S21CommonHandler implements ZZZL0061Constant {

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
        int selected =  getButtonSelectNumber();
        Serializable arg = getArgForSubScreen();
        
        if (arg != null) {
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;
                EZDBStringItem templVal = (EZDBStringItem) params[1];
                templVal.setValue(scrnMsg.A.no(selected).batProcJobId_A.getValue());
                
                if (params.length >= 3) {
                    EZDBStringItem templVal2 = (EZDBStringItem) params[2];
                    templVal2.setValue(scrnMsg.A.no(selected).batProcPgmId_A.getValue());
                }
            }
        }

	}

}
