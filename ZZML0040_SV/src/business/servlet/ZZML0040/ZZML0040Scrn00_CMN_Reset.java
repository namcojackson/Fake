/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0040;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0040.ZZML0040CMsg;
import business.servlet.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0040Scrn00_CMN_Reset extends S21CommonHandler implements ZZML0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount( 0 );
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

		ZZML0040CMsg bizMsg = new ZZML0040CMsg();
		bizMsg.setBusinessID("ZZML0040");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;


	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;
		ZZML0040CMsg bizMsg  = (ZZML0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);


	}

}
