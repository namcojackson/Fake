/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0051;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZOL0051.constant.ZZOL0051Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0051Scrn00_ProcessIdLookup extends S21CommonHandler implements ZZOL0051Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

		//ZZOL0051CMsg bizMsg = new ZZOL0051CMsg();
		//bizMsg.setBusinessID("ZZOL0051");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;
		//ZZOL0051CMsg bizMsg  = (ZZOL0051CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int buttonNo = getButtonSelectNumber();

        Object[] params = new Object[3];
        params[0] = scrnMsg.glblCmpyCd;
        params[1] = scrnMsg.P.no( buttonNo ).menuProcId_P;
        params[2] = scrnMsg.P.no( buttonNo ).upldCsvRstProcNm_P;
        setArgForSubScreen(params);

	}

}
