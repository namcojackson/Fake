package business.servlet.NWAL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.servlet.NWAL2040.NWAL2040BMsg;
import business.servlet.NWAL2040.common.NWAL2040CommonLogic;
import business.servlet.NWAL2040.constant.NWAL2040Constant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NWAL2040_INIT extends S21INITCommonHandler implements NWAL2040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		checkBusinessAppGranted( getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NWAL2040BMsg scrnMsg = (NWAL2040BMsg) bMsg;
//		NWAL2040CommonLogic.setPage(scrnMsg, 1);
		scrnMsg.clearAllGUIAttribute(NWAL2040Constant.SCREEN_ID);
		NWAL2040CMsg bizMsg = new NWAL2040CMsg();
		NWAL2040CommonLogic.setInitialTab(this, getUserProfileService(), scrnMsg);
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
//		scrnMsg.coaProdNm_H1.setInputProtected(true);
//		scrnMsg.rtlWhNm_H1.setInputProtected(true);
//		scrnMsg.rtlSwhNm_H1.setInputProtected(true);
		NWAL2040CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
//		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		
	}

	@Override
	protected void setNameForMessage(EZDBMsg bMsg) {

	}

}
