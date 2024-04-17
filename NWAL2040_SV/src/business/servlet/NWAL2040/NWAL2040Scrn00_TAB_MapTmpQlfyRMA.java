package business.servlet.NWAL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/12   Fujitsu         T.Ogura         Create          QC#19805
 *</pre>
 */
public class NWAL2040Scrn00_TAB_MapTmpQlfyRMA extends S21CommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	    // do nothing.
	}

	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		NWAL2040CMsg bizMsg = new NWAL2040CMsg();
		scrnMsg.clearAllGUIAttribute(NWAL2040Constant.SCREEN_ID);
		scrnMsg.xxDplyTab_H1.setValue(TAB_MAP_TMPL_QLFY_RMA);
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
		return bizMsg;
		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
		NWAL2040CMsg bizMsg  = (NWAL2040CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
	}

}
