/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.*;
import parts.servletcommon.*;
//import business.blap.NLAL0070.NLAL0070CMsg;

import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070_NMAL6050 extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        if (!CLOSE.equals(getLastGuard())) {
            scrnMsg.mdseCd_P1.setValue(scrnMsg.xxPopPrm_K9.getValue());
            scrnMsg.mdseDescShortTxt.setValue(scrnMsg.xxPopPrm_KA.getValue());
        }
	}
}
