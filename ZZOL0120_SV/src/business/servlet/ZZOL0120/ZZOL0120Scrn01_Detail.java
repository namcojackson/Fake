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

public class ZZOL0120Scrn01_Detail extends S21CommonHandler implements ZZOL0120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZOL0120BMsg scrnMsg = (ZZOL0120BMsg) bMsg;
        
        scrnMsg.menuProcGrpCd_C1.setValue(scrnMsg.menuProcGrpCd_B1.getValue());
        scrnMsg.menuProcGrpNm_C1.setValue(scrnMsg.menuProcGrpNm_B1.getValue());
        
        int index = getButtonSelectNumber();
        scrnMsg.menuProcId_C1.setValue(scrnMsg.B.no(index).menuProcId_B2.getValue());
        scrnMsg.menuProcNm_C1.setValue(scrnMsg.B.no(index).menuProcNm_B2.getValue());

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

        ZZOL0120CommonLogic.dspScrn02(scrnMsg, this);

        scrnMsg.upTabCd_C1.clear();
        scrnMsg.upTabNm_C1.clear();
        scrnMsg.upTabSortNum_C1.clear();
        scrnMsg.bizAppId_C1.clear();
        scrnMsg.bizAppNm_C1.clear();
        scrnMsg.myProcUsbleFlg_C1.setValue("Y");
        scrnMsg.upTabUsbleFlg_C1.setValue("Y");
        scrnMsg.ezUpTime_C1.clear();
        scrnMsg.ezUpTimeZone_C1.clear();

        scrnMsg.setFocusItem(scrnMsg.upTabNm_C1);
        
        S21SortColumnIMGController.clearIMG(SCREEN_NAME_02, scrnMsg, scrnMsg.C.no(0).getBaseContents());

    }

}
