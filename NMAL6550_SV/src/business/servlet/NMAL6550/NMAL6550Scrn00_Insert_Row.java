/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6550.NMAL6550CMsg;
import business.servlet.NMAL6550.common.NMAL6550CommonLogic;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6550Scrn00_Insert_Row extends S21CommonHandler implements NMAL6550Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
		NMAL6550CommonLogic.checkDetail(scrnMsg);
		scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        NMAL6550CMsg bizMsg = NMAL6550CommonLogic.setBizFunction("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
		NMAL6550CMsg bizMsg  = (NMAL6550CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6550CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).glblCmpyCd_A1);
	}

}
