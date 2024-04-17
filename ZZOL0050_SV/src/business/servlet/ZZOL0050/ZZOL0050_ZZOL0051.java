/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0050;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZOL0050.ZZOL0050CMsg;
import business.servlet.ZZOL0050.common.ZZOL0050CommonLogic;
import business.servlet.ZZOL0050.constant.ZZOL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0050_ZZOL0051 extends S21CommonHandler implements ZZOL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;
        
        if ( scrnMsg.A.getValidCount() == 0 && !"ZZZM9001E".equals(scrnMsg.xxMsgId.getValue()) ) {
            return null;
        }
        
//        scrnMsg.A.clear();
//        scrnMsg.A.setValidCount(0);
//        scrnMsg.xxPageShowFromNum.clear();
//        scrnMsg.xxPageShowToNum.clear();
//        scrnMsg.xxPageShowOfNum.clear();

        ZZOL0050CMsg bizMsg = new ZZOL0050CMsg();
		bizMsg.setBusinessID("ZZOL0050");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0050BMsg scrnMsg = (ZZOL0050BMsg) bMsg;
		ZZOL0050CMsg bizMsg  = (ZZOL0050CMsg) cMsg;

        if ( bizMsg != null ) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        setButtonEnabled(CMN_BTN7[0], scrnMsg.A.getValidCount() > 0);
        ZZOL0050CommonLogic.setTableColor( scrnMsg );
        scrnMsg.setFocusItem( scrnMsg.glblCmpyCd );
	}

}
