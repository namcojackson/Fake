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
package business.servlet.NMAL6450;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6450.NMAL6450CMsg;
import business.servlet.NMAL6450.common.NMAL6450CommonLogic;
import business.servlet.NMAL6450.constant.NMAL6450Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6450_INIT extends S21INITCommonHandler implements NMAL6450Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;

		NMAL6450CMsg bizMsg = new NMAL6450CMsg();
		bizMsg.setBusinessID("NMAL6450");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;
		NMAL6450CMsg bizMsg  = (NMAL6450CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).pmtTermSortNum_A1);
        NMAL6450CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

	}

	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).pmtTermSortNum_A1.setNameForMessage("Sort Num");
            scrnMsg.A.no(i).pmtTermCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).pmtTermNm_A1.setNameForMessage("Name");
            scrnMsg.A.no(i).pmtTermDescTxt_A1.setNameForMessage("Description Text");
            scrnMsg.A.no(i).pmtTermAot_A1.setNameForMessage("Amount of Time");
        }
    }
}
