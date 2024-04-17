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

public class ZZBL0020Scrn00_Edit extends S21CommonHandler implements ZZBL0020Constant {


	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;

		int i = getButtonSelectNumber();
		
		// Set ezReqBusinessID_B selected ezReqBusinessID_A
		scrnMsg.ezReqBusinessID_B.setValue(scrnMsg.A.no(i).ezReqBusinessID_A.getValue());
		scrnMsg.ezReqBusinessID_B.setInputProtected(true);
		scrnMsg.ezReqBusinessName_B.setValue(scrnMsg.A.no(i).ezReqBusinessName_A.getValue());
		scrnMsg.ezReqCountPerJob_B.setValue(scrnMsg.A.no(i).ezReqCountPerJob_A.getValue());
		scrnMsg.ezReqJobConcurrency_B.setValue(scrnMsg.A.no(i).ezReqJobConcurrency_A.getValue());
		scrnMsg.ezReqJobCtlNetID_B.setValue(scrnMsg.A.no(i).ezReqJobCtlNetID_A.getValue());
		scrnMsg.ezReqJobCtlNetID_B.setInputProtected(true);
		scrnMsg.ezReqJobErrorCtlFlag_B.setValue(scrnMsg.A.no(i).ezReqJobErrorCtlFlag_A.getValue());		
		scrnMsg.ezReqJobStopFlag_B.setValue(scrnMsg.A.no(i).ezReqJobStopFlag_A.getValue());
		scrnMsg.actvFlg_B.setValue(EDITED);
		
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