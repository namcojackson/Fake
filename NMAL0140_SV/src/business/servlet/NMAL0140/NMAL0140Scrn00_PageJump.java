package business.servlet.NMAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0140.NMAL0140CMsg;
import business.servlet.NMAL0140.common.NMAL0140CommonLogic;
import business.servlet.NMAL0140.constant.NMAL0140Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/13/2017   Fujitsu         K.Ishizuka      Update          QC#17508
 *</pre>
 */
public class NMAL0140Scrn00_PageJump extends S21CommonHandler implements NMAL0140Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		//S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg,
		S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, // MOD S21_NA #17508
				scrnMsg.A, 
				scrnMsg.xxPageShowFromNum_10,
				scrnMsg.xxPageShowToNum_10, 
				scrnMsg.xxPageShowOfNum_10,
				scrnMsg.xxPageShowCurNum_10, 
				scrnMsg.xxPageShowTotNum_10);
		
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		//S21SequentialSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg,
		S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, // MOD S21_NA #17508
				scrnMsg.A, 
				scrnMsg.xxPageShowFromNum_10, 
				scrnMsg.xxPageShowToNum_10,
				scrnMsg.xxPageShowOfNum_10, 
				scrnMsg.xxPageShowCurNum_10,
				scrnMsg.xxPageShowTotNum_10);

		NMAL0140CMsg bizMsg = new NMAL0140CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		NMAL0140CMsg bizMsg  = (NMAL0140CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0140CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		
	}

}
