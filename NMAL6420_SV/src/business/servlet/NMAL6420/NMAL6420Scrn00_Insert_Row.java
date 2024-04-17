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
package business.servlet.NMAL6420;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6420.NMAL6420CMsg;
import business.servlet.NMAL6420.common.NMAL6420CommonLogic;
import business.servlet.NMAL6420.constant.NMAL6420Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6420Scrn00_Insert_Row extends S21CommonHandler implements NMAL6420Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL6420BMsg scrnMsg = (NMAL6420BMsg) bMsg;
        NMAL6420CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6420BMsg scrnMsg = (NMAL6420BMsg) bMsg;

		NMAL6420CMsg bizMsg = new NMAL6420CMsg();
		bizMsg.setBusinessID("NMAL6420");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6420BMsg scrnMsg = (NMAL6420BMsg) bMsg;
		NMAL6420CMsg bizMsg  = (NMAL6420CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6420CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        NMAL6420CommonLogic.addCheckItemForDoProcess(scrnMsg);
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).locRoleTpCd_A1);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).locRoleTpCd_A1);

	}

}
