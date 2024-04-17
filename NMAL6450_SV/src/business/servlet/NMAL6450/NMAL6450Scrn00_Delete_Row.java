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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6450Scrn00_Delete_Row extends S21CommonHandler implements NMAL6450Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//NMAL6450BMsg scrnMsg = (NMAL6450BMsg) bMsg;


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
        NMAL6450CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6450Bean.xxChkBox_A1}, 1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(0).pmtTermSortNum_A1);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        scrnMsg.setMessageInfo(MESSAGE_ID.NZZM0002I.toString());

	}

}