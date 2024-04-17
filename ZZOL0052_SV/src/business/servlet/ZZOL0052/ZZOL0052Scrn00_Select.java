/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0052;

import java.io.Serializable;

import parts.common.*;
import parts.servletcommon.*;

//import business.blap.ZZOL0052.ZZOL0052CMsg;
import business.servlet.ZZOL0052.constant.ZZOL0052Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0052Scrn00_Select extends S21CommonHandler implements ZZOL0052Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;

		//ZZOL0052CMsg bizMsg = new ZZOL0052CMsg();
		//bizMsg.setBusinessID("ZZOL0052");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;
        
        int selectNo = getButtonSelectNumber();

        Serializable arg = getArgForSubScreen();
        if (arg != null && arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem menuProcId = (EZDBStringItem) params[1];
            menuProcId.setValue(scrnMsg.A.no(selectNo).menuProcId_A.getValue());
            EZDBStringItem menuProcNm = (EZDBStringItem) params[2];
            menuProcNm.setValue(scrnMsg.A.no(selectNo).menuProcNm_A.getValue());
        }

	}

}
