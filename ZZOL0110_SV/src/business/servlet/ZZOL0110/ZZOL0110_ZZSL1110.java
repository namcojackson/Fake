/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0110.ZZOL0110CMsg;
import business.servlet.ZZOL0110.constant.ZZOL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0110_ZZSL1110 extends S21CommonHandler implements ZZOL0110Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
       ZZOL0110BMsg scrnMsg = (ZZOL0110BMsg) bMsg;

       ZZOL0110CMsg bizMsg = new ZZOL0110CMsg();
       bizMsg.setBusinessID("ZZOL0110");
       bizMsg.setFunctionCode("20");
       EZDMsg.copy(scrnMsg, null, bizMsg, null);

       return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

	   ZZOL0110BMsg scrnMsg = (ZZOL0110BMsg) bMsg;
       ZZOL0110CMsg bizMsg  = (ZZOL0110CMsg) cMsg;

       EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}

}
