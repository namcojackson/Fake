/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0130;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0130.ZZOL0130CMsg;
import business.servlet.ZZOL0130.common.ZZOL0130CommonLogic;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0130Scrn00_Upd extends S21CommonHandler implements ZZOL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
		
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.delTblId);
        scrnMsg.addCheckItem(scrnMsg.specEffCancCd_L);
        scrnMsg.addCheckItem(scrnMsg.specEffMthAot);
        scrnMsg.addCheckItem(scrnMsg.delExecDt);
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

		ZZOL0130CMsg bizMsg = new ZZOL0130CMsg();
		bizMsg.setBusinessID("ZZOL0130");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
		ZZOL0130CMsg bizMsg  = (ZZOL0130CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.delTblId);
        scrnMsg.addCheckItem(scrnMsg.specEffMthAot);
        scrnMsg.putErrorScreen();
        
        ZZOL0130CommonLogic.dspScrn(this, scrnMsg);
	}

}
