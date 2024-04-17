/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0100;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0100.common.ZZOL0100CommonLogic;
import business.servlet.ZZOL0100.constant.ZZOL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0100Scrn00_CMN_Clear extends S21CommonHandler implements ZZOL0100Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        
        ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;
        
        scrnMsg.menuInfoPk.clear();
        scrnMsg.menuInfoTxt.setValue("");
        scrnMsg.xxChkBox.setValue("N");
        scrnMsg.menuEffFromDt.setValue("");
        scrnMsg.menuEffFromTm_F3.setValue("");
        scrnMsg.menuEffThruDt.setValue("");
        scrnMsg.menuEffThruTm_T3.setValue("");
        scrnMsg.menuInfoSortNum.clear();
        
        ZZOL0100CommonLogic.dspScrn(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.menuInfoTxt);

	}

}
