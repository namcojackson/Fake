/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0030;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0030.common.ZZZL0030CommonLogic;
import business.servlet.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0030Scrn00_CMN_Clear extends S21CommonHandler implements ZZZL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg) bMsg;
        // Clear Header section.
        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());
        scrnMsg.opsUsrId.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        ZZZL0030CommonLogic.initPullDowns(scrnMsg);
        this.setButtonEnabled("View", false);
	}
}
