/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/15   CUSA            FJ)A.Akabane    Create          N/A
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_OpenSubWin_Mdse extends S21CommonHandler implements NLAL0070Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        scrnMsg.xxPopPrm_K0.setValue(MDSE_FOR_SCE_V);
        scrnMsg.xxPopPrm_K1.setValue(MDSE_CD);
        scrnMsg.xxPopPrm_K2.setValue(MDSE_NM);
        scrnMsg.xxPopPrm_K3.setValue(MDSE_CD);
        scrnMsg.xxPopPrm_K4.setValue(MDSE_FOR_SCE_V);
        scrnMsg.xxPopPrm_K5.setValue(MDSE_CD);
        scrnMsg.xxPopPrm_K6.setValue(MDSE_NM);
        scrnMsg.xxPopPrm_K7.setValue(MDSE_CD);
        scrnMsg.xxPopPrm_K8.setValue(MDSE_NM);
        scrnMsg.xxPopPrm_K9.clear();
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_P1)) {
            scrnMsg.xxPopPrm_K9.setValue(scrnMsg.mdseCd_P1.getValue());
        }
        scrnMsg.xxPopPrm_KA.clear();

		Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_K0;
        params[1] = scrnMsg.xxPopPrm_K1;
        params[2] = scrnMsg.xxPopPrm_K2;
        params[3] = scrnMsg.xxPopPrm_K3;
        params[4] = scrnMsg.xxPopPrm_K4;
        params[5] = scrnMsg.xxPopPrm_K5;
        params[6] = scrnMsg.xxPopPrm_K6;
        params[7] = scrnMsg.xxPopPrm_K7;
        params[8] = scrnMsg.xxPopPrm_K8;
        params[9] = scrnMsg.xxPopPrm_K9;
        params[10] = scrnMsg.xxPopPrm_KA;

        setArgForSubScreen(params);
	}

}
