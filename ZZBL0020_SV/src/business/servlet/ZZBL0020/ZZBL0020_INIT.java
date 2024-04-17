/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZBL0020.ZZBL0020CMsg;
import business.servlet.ZZBL0020.common.ZZBL0020CommonLogic;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZBL0020_INIT extends S21CommonHandler implements ZZBL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;

		ZZBL0020CMsg bizMsg = new ZZBL0020CMsg();
		bizMsg.setBusinessID("ZZBL0020");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;
		ZZBL0020CMsg bizMsg  = (ZZBL0020CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
		// Same as ZZBL0020Scrn01
		ZZBL0020CommonLogic.initCommonButtonForScrn00(this, scrnMsg);

        scrnMsg.ezReqBusinessID.setNameForMessage("Request JOB NET ID");
        scrnMsg.ezReqJobCtlNetID.setNameForMessage("Request Control JOB NET ID");

        scrnMsg.ezReqBusinessID_B.setNameForMessage("JOB NET ID");
        scrnMsg.ezReqBusinessName_B.setNameForMessage("JOB NET Name");
        scrnMsg.ezReqJobCtlNetID_B.setNameForMessage("Sceduled Control JOB NET ID");
        scrnMsg.ezReqCountPerJob_B.setNameForMessage("Max Counter");
	}
}