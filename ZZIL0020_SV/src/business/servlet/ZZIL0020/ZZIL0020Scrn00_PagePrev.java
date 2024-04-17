/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0020;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0020.ZZIL0020CMsg;
import business.servlet.ZZIL0020.common.ZZIL0020CommonLogic;
import business.servlet.ZZIL0020.constant.ZZIL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0020Scrn00_PagePrev extends S21CommonHandler implements ZZIL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        scrnMsg.xxPageShowFromNum.setValue( scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.X.length() - 1 );
        scrnMsg.xxPageShowToNum.clear();

		ZZIL0020CMsg bizMsg = new ZZIL0020CMsg();
		bizMsg.setBusinessID("ZZIL0020");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;
		ZZIL0020CMsg bizMsg  = (ZZIL0020CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZIL0020CommonLogic.setTableColor( scrnMsg );

	}

}
