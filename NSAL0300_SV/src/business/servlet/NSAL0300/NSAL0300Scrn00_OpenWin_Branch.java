/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Branch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/01/21 T.Tomita [QC#2182, DEL]
//        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
//        scrnMsg.putErrorScreen();
        return;
        // START 2016/01/21 T.Tomita [QC#2182, DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        // START 2016/01/21 T.Tomita [QC#2182, MOD]
        Object[] prm = new Object[9];
        prm[0] = scrnMsg.xxPopPrm_00;
        prm[1] = scrnMsg.xxPopPrm_01;
        prm[2] = scrnMsg.svcContrBrCd;
        prm[3] = scrnMsg.svcContrBrDescTxt;
        prm[4] = scrnMsg.xxPopPrm_04;
        prm[5] = scrnMsg.xxPopPrm_05;
        prm[6] = scrnMsg.dsContrDtlPk_P;
     // START 2017/01/24 N.Arai [QC#17228, MOD]
        // prm[7] = scrnMsg.xxPopPrm_07;
        prm[7] = scrnMsg.contrAdminPsnCd;
     // END 2017/01/24 N.Arai [QC#17228, MOD]
        prm[8] = scrnMsg.svcLineBizCd;
        setArgForSubScreen(prm);
        // START 2016/01/21 T.Tomita [QC#2182, MOD]
    }
}
