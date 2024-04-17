/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2011/10/27   CSAI            N.Sasaki        Create          362045
 *</pre>
 */
package business.servlet.NPAL0100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0100.NPAL0100CMsg;
import business.servlet.NPAL0100.common.NPAL0100CommonLogic;
import business.servlet.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NPAL0100Scrn00_CMN_Submit extends S21CommonHandler implements NPAL0100Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

		NPAL0100CMsg bizMsg = new NPAL0100CMsg();
		bizMsg.setBusinessID("NPAL0100");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;
		NPAL0100CMsg bizMsg  = (NPAL0100CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);

//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
//        }
//        scrnMsg.putErrorScreen();
        
        if (scrnMsg.getMessageCode().equals("")) {
            scrnMsg.setMessageInfo("ZZM8100I");
            NPAL0100CommonLogic.afterSubmit(this, scrnMsg);
            NPAL0100CommonLogic.enableSubmitButton(this, scrnMsg, false);
        } else {
            NPAL0100CommonLogic.initializeForError(this, scrnMsg);
        }
	}

}
