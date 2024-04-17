/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0130;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0130.common.ZZOL0130CommonLogic;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0130Scrn00_CMN_Clear extends S21CommonHandler implements ZZOL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

 		return null;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

        scrnMsg.delTblId_A0.clear();
        
        scrnMsg.delTblConfigPk.clear();
        scrnMsg.delTblId.clear();
        scrnMsg.specEffCancCd_L.clear();
        scrnMsg.specEffMthAot.clear();
        scrnMsg.delTblCmntTxt.clear();
        scrnMsg.delExecDt.clear();
        scrnMsg.ezUpTime.clear();
        scrnMsg.ezUpTimeZone.clear();
        
        for(int i=0;i<scrnMsg.A.getValidCount();i++){
            scrnMsg.A.no(i).xxChkBox_A1.clear();
        }

        ZZOL0130CommonLogic.dspScrn(this, scrnMsg);

	}

}
