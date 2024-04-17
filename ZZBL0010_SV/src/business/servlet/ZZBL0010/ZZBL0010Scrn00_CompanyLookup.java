/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZBL0010;


import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsgAccessor;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZBL0010Scrn00_CompanyLookup extends S21CommonHandler implements ZZBL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		//ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;

		//ZZBL0010CMsg bizMsg = new ZZBL0010CMsg();
		//bizMsg.setBusinessID("ZZBL0010");
		//bizMsg.setFunctionCode("20");
		//EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		//return bizMsg;

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
		//ZZBL0010CMsg bizMsg  = (ZZBL0010CMsg) cMsg;

		//EZDMsg.copy(bizMsg, null, scrnMsg, null);
	    Object[] params = new Object[2];
          
	    params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm"); 
	    ((EZDBStringItem) params[0]).setValue("Global Company Code");
	      
	    params[1] = scrnMsg.glblCmpyCd;
	    setArgForSubScreen(params);

	}

}
