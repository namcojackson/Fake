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
package business.servlet.NMAL6370;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6370.NMAL6370CMsg;
import business.servlet.NMAL6370.common.NMAL6370CommonLogic;
import business.servlet.NMAL6370.constant.NMAL6370Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6370_INIT extends S21INITCommonHandler implements NMAL6370Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6370BMsg scrnMsg = (NMAL6370BMsg) bMsg;

		NMAL6370CMsg bizMsg = new NMAL6370CMsg();
		bizMsg.setBusinessID("NMAL6370");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6370BMsg scrnMsg = (NMAL6370BMsg) bMsg;
		NMAL6370CMsg bizMsg  = (NMAL6370CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacTpSortNum_A1);
        NMAL6370CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

	}

	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6370BMsg scrnMsg = (NMAL6370BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ctacTpSortNum_A1.setNameForMessage("Sort Num");
            scrnMsg.A.no(i).ctacTpCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).ctacTpNm_A1.setNameForMessage("Name");
        }
    }
}