package business.servlet.NWCL0130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0130.NWCL0130CMsg;
import business.servlet.NWCL0130.NWCL0130BMsg;
import business.servlet.NWCL0130.common.NWCL0130CommonLogic;
import business.servlet.NWCL0130.constant.NWCL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWCL0130Scrn00_CMN_Reset extends S21CommonHandler implements NWCL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;
		NWCL0130CMsg bizMsg = new NWCL0130CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NWCL0130BMsg scrnMsg = (NWCL0130BMsg) bMsg;
		NWCL0130CMsg bizMsg  = (NWCL0130CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NWCL0130CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
//		scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
		
	}

}
