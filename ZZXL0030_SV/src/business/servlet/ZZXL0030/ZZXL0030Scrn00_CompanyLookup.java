/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZXL0030;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsgAccessor;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZXL0030.constant.ZZXL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0030Scrn00_CompanyLookup extends S21CommonHandler implements ZZXL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
	    Object[] params = new Object[2];
          
	    params[0] = EZDMsgAccessor.createItem(scrnMsg, "xxScrNm"); 
	    ((EZDBStringItem) params[0]).setValue("Global Company Code");
	      
	    params[1] = scrnMsg.glblCmpyCd;
	    setArgForSubScreen(params);
	}
}
