/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0120;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0120.common.ZZOL0120CommonLogic;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0120Scrn02_CMN_Clear extends S21CommonHandler implements ZZOL0120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;

        scrnMsg.upTabCd_C1.clear();
        scrnMsg.upTabNm_C1.clear();
        scrnMsg.upTabSortNum_C1.clear();
        scrnMsg.bizAppId_C1.clear();
        scrnMsg.bizAppNm_C1.clear();
        scrnMsg.myProcUsbleFlg_C1.setValue("Y");
        scrnMsg.upTabUsbleFlg_C1.setValue("Y");
        scrnMsg.ezUpTime_C1.clear();
        scrnMsg.ezUpTimeZone_C1.clear();

        ZZOL0120CommonLogic.dspScrn02(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.upTabNm_C1);


	}

}
