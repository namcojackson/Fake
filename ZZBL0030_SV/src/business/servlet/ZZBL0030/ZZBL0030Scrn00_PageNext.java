/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */
package business.servlet.ZZBL0030;

import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZBL0030.ZZBL0030CMsg;
import business.servlet.ZZBL0030.common.ZZBL0030CommonLogic;
import business.servlet.ZZBL0030.constant.ZZBL0030Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZBL0030Scrn00_PageNext extends S21CommonHandler implements ZZBL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0030BMsg scrnMsg = (ZZBL0030BMsg) bMsg;

		ZZBL0030CMsg bizMsg = new ZZBL0030CMsg();
		bizMsg.setBusinessID("ZZBL0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0030BMsg scrnMsg = (ZZBL0030BMsg) bMsg;
		ZZBL0030CMsg bizMsg  = (ZZBL0030CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
		ZZBL0030CommonLogic.setTableColor( scrnMsg );
	}
}