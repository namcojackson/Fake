/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0070.ZZZL0070CMsg;
import business.servlet.ZZZL0070.common.ZZZL0070CommonLogic;
import business.servlet.ZZZL0070.constant.ZZZL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0070Scrn00_JobIdLookup extends S21CommonHandler implements ZZZL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) bMsg;
        ZZZL0070CommonLogic.checkItem(scrnMsg);
        ZZZL0070CommonLogic.correlativeTimeCheck(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) bMsg;

        Object[] param = new Object[3];
        param[0] = scrnMsg.glblCmpyCd;
        param[1] = scrnMsg;

        setArgForSubScreen(param);
	}
}
