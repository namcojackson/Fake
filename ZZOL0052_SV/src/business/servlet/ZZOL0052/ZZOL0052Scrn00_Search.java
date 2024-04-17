/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0052;

import parts.common.*;
import parts.servletcommon.*;

//import business.blap.ZZOL0052.ZZOL0052CMsg;
import business.blap.ZZOL0052.ZZOL0052CMsg;
import business.servlet.ZZOL0052.common.ZZOL0052CommonLogic;
import business.servlet.ZZOL0052.constant.ZZOL0052Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0052Scrn00_Search extends S21CommonHandler implements ZZOL0052Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.upldCsvRstProcNm);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;

		ZZOL0052CMsg bizMsg = new ZZOL0052CMsg();
		bizMsg.setBusinessID("ZZOL0052");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;
		ZZOL0052CMsg bizMsg  = (ZZOL0052CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZOL0052CommonLogic.setTableColor( scrnMsg );
        scrnMsg.setFocusItem( scrnMsg.upldCsvRstProcNm );
	}

}
