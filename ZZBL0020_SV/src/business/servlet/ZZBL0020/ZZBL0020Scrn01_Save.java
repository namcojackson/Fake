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
import business.blap.ZZBL0020.ZZBL0020CMsg;
import business.servlet.ZZBL0020.common.ZZBL0020CommonLogic;
import business.servlet.ZZBL0020.constant.ZZBL0020Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZBL0020Scrn01_Save extends S21CommonHandler implements ZZBL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		ZZBL0020BMsg scrnMsg = (ZZBL0020BMsg) bMsg;
		
		// Check max counter #
		if (scrnMsg.ezReqCountPerJob_B.isClear()){
			scrnMsg.ezReqCountPerJob_B.setErrorInfo(1, "ZZM9033E", new String[] {"Max counter"});
			scrnMsg.addCheckItem(scrnMsg.ezReqCountPerJob_B);
		}
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

        if (bizMsg.getMessageKind().equals("E")) { // fail
            throw new EZDFieldErrorException();
        }		
		if (bizMsg.A.getValidCount() > 0 ) {
			EZDMsg.copy(bizMsg, null, scrnMsg, null);
			ZZBL0020CommonLogic.setTableColor( scrnMsg );
			ZZBL0020CommonLogic.initCommonButtonForScrn00(this, scrnMsg);
		}
	}
}