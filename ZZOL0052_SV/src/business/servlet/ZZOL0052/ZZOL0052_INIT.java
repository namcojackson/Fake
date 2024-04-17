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
import business.blap.ZZOL0052.ZZOL0052CMsg;

import business.servlet.ZZOL0052.common.ZZOL0052CommonLogic;
import business.servlet.ZZOL0052.constant.ZZOL0052Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZOL0052_INIT extends S21CommonHandler implements ZZOL0052Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZOL0052BMsg scrnMsg = (ZZOL0052BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
               Object[] params = (Object[])arg;
               
               scrnMsg.glblCmpyCd.setValue(((EZDBStringItem) params[0]).getValue());
            }
        } else {
            String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            if (gcc != null || gcc.length() > 0) {
                scrnMsg.glblCmpyCd.setValue(gcc);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
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

        ZZOL0052CommonLogic.setButtonScrn00( this );
        ZZOL0052CommonLogic.setTableColor( scrnMsg );
        scrnMsg.setFocusItem( scrnMsg.upldCsvRstProcNm );
	}

}
