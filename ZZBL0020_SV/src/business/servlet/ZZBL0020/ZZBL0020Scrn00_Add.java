/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0020;

import parts.common.*;
import parts.servletcommon.*;
import business.servlet.ZZBL0020.common.ZZBL0020CommonLogic;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZBL0020Scrn00_Add extends S21CommonHandler implements ZZBL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;

		// Set ezReqBusinessID_B null
		scrnMsg.ezReqBusinessID_B.clear();
		scrnMsg.ezReqBusinessID_B.setInputProtected(false);
		scrnMsg.ezReqBusinessName_B.clear();
		scrnMsg.ezReqJobCtlNetID_B.clear();
		scrnMsg.ezReqJobCtlNetID_B.setInputProtected(false);
		scrnMsg.ezReqJobErrorCtlFlag_B.clear();
		scrnMsg.ezReqJobConcurrency_B.clear();
		scrnMsg.ezReqJobStopFlag_B.clear();
		scrnMsg.ezReqCountPerJob_B.setValue(100);
		scrnMsg.actvFlg_B.setValue(ADDED);
		
		ZZBL0020CommonLogic.initCommonButtonForScrn01(this);
		
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
		//ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;
		//ZZBL0020CMsg bizMsg  = (ZZBL0020CMsg) cMsg;
		//EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}
}