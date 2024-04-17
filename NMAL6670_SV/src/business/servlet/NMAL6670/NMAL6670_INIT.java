/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
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

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6670_INIT extends S21INITCommonHandler implements NMAL6670Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

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

        scrnMsg.setFocusItem(scrnMsg.A.no(0).istlPmtTermCd_A1);
        NMAL6670CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

	}

	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6670BMsg scrnMsg = (NMAL6670BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).istlPmtTermCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).istlPmtTermDtlNum_A1.setNameForMessage("Detail Number");
            scrnMsg.A.no(i).istlPmtTermAot_A1.setNameForMessage("Amount of Time");
            scrnMsg.A.no(i).istlPmtTermPct_A1.setNameForMessage("Percent");
        }
    }
}