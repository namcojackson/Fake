/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 *</pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL4500Scrn00_CMN_Return extends S21CommonHandler implements NMAL4500Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

	}

}
