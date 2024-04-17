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

public class ZZOL0100Scrn00_Edit extends S21CommonHandler implements ZZOL0100Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0100BMsg scrnMsg = (ZZOL0100BMsg) bMsg;

        int index = getButtonSelectNumber();

        scrnMsg.menuInfoPk.setValue(scrnMsg.A.no(index).menuInfoPk_A1.getValue());
        scrnMsg.menuInfoTxt.setValue(scrnMsg.A.no(index).menuInfoTxt_A1.getValue());
        scrnMsg.xxChkBox.setValue(scrnMsg.A.no(index).menuInfoVwbleFlg_A1.getValue());
        scrnMsg.menuEffFromDt.setValue(scrnMsg.A.no(index).xxFromDt_A1.getValue());
        scrnMsg.menuEffFromTm_F3.setValue(scrnMsg.A.no(index).menuEffFromTm_A1.getValue());
        scrnMsg.menuEffThruDt.setValue(scrnMsg.A.no(index).xxThruDt_A2.getValue());
        scrnMsg.menuEffThruTm_T3.setValue(scrnMsg.A.no(index).menuEffThruTm_A2.getValue());
        scrnMsg.menuInfoSortNum.setValue(scrnMsg.A.no(index).menuInfoSortNum_A1.getValue());
        scrnMsg.ezUpTime.setValue(scrnMsg.A.no(index).ezUpTime_A1.getValue());
        scrnMsg.ezUpTimeZone.setValue(scrnMsg.A.no(index).ezUpTimeZone_A1.getValue());
        
        ZZOL0100CommonLogic.dspScrn(scrnMsg, this);
        
        scrnMsg.setFocusItem(scrnMsg.menuInfoTxt);

	}

}
