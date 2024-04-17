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
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL4500Scrn00_OpenWin_Vendor_CD extends S21CommonHandler implements NMAL4500Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        scrnMsg.xxScrEventNm_01.setValue(POPUP_ACTION_VENDOR);

        // Init param
        NMAL4500CommonLogic.setInitParam_Vnd(scrnMsg);

        // Pass param
        Object[] params = NMAL4500CommonLogic.getParamOpenWin_NMAL6050(scrnMsg);

        setArgForSubScreen(params);

	}

}
