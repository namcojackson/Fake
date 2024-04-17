/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL6460;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6460.constant.NMAL6460Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6460Scrn00_OpenWin_CashDiscountTerm extends S21CommonHandler implements NMAL6460Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;
		
        int idx = getButtonSelectNumber();

        scrnMsg.xxListNum.setValue(idx);

        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(idx).cashDiscTermCd_A1;

        setArgForSubScreen(params);

	}

}
