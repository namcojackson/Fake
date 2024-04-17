/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/02   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL6460;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6460.NMAL6460CMsg;
import business.servlet.NMAL6460.common.NMAL6460CommonLogic;
import business.servlet.NMAL6460.constant.NMAL6460Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6460Scrn00_PagePrev extends S21CommonHandler implements NMAL6460Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
        NMAL6460CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;

		NMAL6460CMsg bizMsg = new NMAL6460CMsg();
		bizMsg.setBusinessID("NMAL6460");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
		NMAL6460CMsg bizMsg  = (NMAL6460CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6460CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        NMAL6460CommonLogic.addCheckItemForDoProcess(scrnMsg);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).pmtTermCashDiscSortNum_A1);

	}

}
