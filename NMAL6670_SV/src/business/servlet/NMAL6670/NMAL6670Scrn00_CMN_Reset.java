/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/14   Fujitsu         K.Kimura        Create          WDS#1458 Installment Invoice modification
 *</pre>
 */
package business.servlet.NMAL6670;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6670.NMAL6670CMsg;
import business.servlet.NMAL6670.NMAL6670BMsg;
import business.servlet.NMAL6670.common.NMAL6670CommonLogic;
import business.servlet.NMAL6670.constant.NMAL6670Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6670Scrn00_CMN_Reset extends S21CommonHandler implements NMAL6670Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6670BMsg scrnMsg = (NMAL6670BMsg) bMsg;

		NMAL6670CMsg bizMsg = new NMAL6670CMsg();
		bizMsg.setBusinessID("NMAL6670");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6670BMsg scrnMsg = (NMAL6670BMsg) bMsg;
		NMAL6670CMsg bizMsg  = (NMAL6670CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6670CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
	}

}
