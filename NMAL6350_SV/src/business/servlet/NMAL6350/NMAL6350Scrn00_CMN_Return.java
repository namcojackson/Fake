/*
 *  <Pre>Copyright (c) 20010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6350;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6350.constant.NMAL6350Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6350Scrn00_CMN_Return extends S21CommonHandler implements NMAL6350Constant {

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
