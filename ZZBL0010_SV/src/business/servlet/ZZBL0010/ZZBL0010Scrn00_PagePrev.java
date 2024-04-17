/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZBL0010.ZZBL0010CMsg;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZBL0010Scrn00_PagePrev extends S21CommonHandler implements ZZBL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;

//		 set values to items of pagenation for prev page
//		scrnMsg.A.clear();
//		scrnMsg.A.setValidCount( 0 );
		// Calculate the previous page's top record
//		scrnMsg.xxPageShowFromNum.setValue( scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1 );
//		scrnMsg.xxPageShowToNum.clear();
		
		ZZBL0010CMsg bizMsg = new ZZBL0010CMsg();
		bizMsg.setBusinessID("ZZBL0010");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
		ZZBL0010CMsg bizMsg  = (ZZBL0010CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
	}
}
