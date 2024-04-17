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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.servlet.ZZOL0120.common.ZZOL0120CommonLogic;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0120Scrn00_Detail extends S21CommonHandler implements ZZOL0120Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;

        int index = getButtonSelectNumber();
        scrnMsg.menuProcGrpCd_B1.setValue(scrnMsg.A.no(index).menuProcGrpCd_A2.getValue());
        scrnMsg.menuProcGrpNm_B1.setValue(scrnMsg.A.no(index).menuProcGrpNm_A2.getValue());

        ZZOL0120CMsg bizMsg = new ZZOL0120CMsg();
        bizMsg.setBusinessID("ZZOL0120");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
        ZZOL0120CMsg bizMsg  = (ZZOL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.menuProcId_B1.clear();
        scrnMsg.menuProcNm_B1.clear();
        scrnMsg.xxChkBox_B1.clear();
        scrnMsg.othSysUrl_B1.clear();
        
        ZZOL0120CommonLogic.dspScrn01(scrnMsg, this);

        scrnMsg.setFocusItem(scrnMsg.menuProcNm_B1);
        
        S21SortColumnIMGController.clearIMG(SCREEN_NAME_01, scrnMsg, scrnMsg.A.no(0).getBaseContents());

	}

}
