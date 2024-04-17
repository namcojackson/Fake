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

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6420_INIT extends S21INITCommonHandler implements NMAL6420Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

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

        scrnMsg.setFocusItem(scrnMsg.A.no(0).locRoleTpSortNum_A1);
        NMAL6420CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

	}

	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6420BMsg scrnMsg = (NMAL6420BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).locRoleTpSortNum_A1.setNameForMessage("Sort Num");
            scrnMsg.A.no(i).locRoleTpCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).locRoleTpNm_A1.setNameForMessage("Name");
        }
    }
}
