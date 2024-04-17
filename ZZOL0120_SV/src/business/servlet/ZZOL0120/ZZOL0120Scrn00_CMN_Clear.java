/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0120;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0120.common.ZZOL0120CommonLogic;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0120Scrn00_CMN_Clear extends S21CommonHandler implements ZZOL0120Constant {

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
        
        scrnMsg.menuProcGrpCd.clear();
        scrnMsg.menuProcGrpNm.clear();
        scrnMsg.menuProcGrpSortNum.clear();
        scrnMsg.menuProcGrpDescTxt.clear();
        scrnMsg.wfAppNm.clear();
        
        ZZOL0120CommonLogic.dspScrn00(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.menuProcGrpNm);
	}

}
