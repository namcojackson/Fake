package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7120.NMAL7120CMsg;
import business.servlet.NMAL7120.NMAL7120BMsg;
import business.servlet.NMAL7120.common.NMAL7120CommonLogic;
import business.servlet.NMAL7120.constant.NMAL7120Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create 
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)         
 *</pre>
 */
public class NMAL7120Scrn00_PageJump extends S21CommonHandler implements NMAL7120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
        NMAL7120CommonLogic.checkInput_Submit(ctx, scrnMsg);

		S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg,
				scrnMsg.A, 
				scrnMsg.xxPageShowFromNum_10,
				scrnMsg.xxPageShowToNum_10, 
				scrnMsg.xxPageShowOfNum_10,
				scrnMsg.xxPageShowCurNum_10, 
				scrnMsg.xxPageShowTotNum_10);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;

		//S21_NA DEL START QC#20206(L3#269)
//		S21SequentialSearchPagenationScrnSupport.setRequestDataForPageJumpNotClear(scrnMsg,
//				scrnMsg.A, 
//				scrnMsg.xxPageShowFromNum_10, 
//				scrnMsg.xxPageShowToNum_10,
//				scrnMsg.xxPageShowOfNum_10, 
//				scrnMsg.xxPageShowCurNum_10,
//				scrnMsg.xxPageShowTotNum_10);
		//S21_NA DEL END QC#20206(L3#269)

		NMAL7120CMsg bizMsg = new NMAL7120CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
		NMAL7120CMsg bizMsg  = (NMAL7120CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

		NMAL7120CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
	}

}
