/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
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
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/11/19   Hitachi         T.Kanasaka      Update          QC893
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_ContractNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.dsContrDtlPk_P.clear();
        Object[] prm = new Object[12];
        prm[0] = scrnMsg.dsContrNum;
        prm[1] = scrnMsg.xxPopPrm_01;
        prm[2] = scrnMsg.xxPopPrm_02;
        prm[3] = scrnMsg.xxPopPrm_03;
        prm[4] = scrnMsg.xxPopPrm_04;
        prm[5] = scrnMsg.xxPopPrm_05;
        prm[6] = scrnMsg.xxPopPrm_06;
        prm[7] = scrnMsg.xxPopPrm_07;
        prm[8] = scrnMsg.xxPopPrm_08;
        prm[9] = scrnMsg.xxPopPrm_09;
        prm[10] = scrnMsg.dsContrPk;
        prm[11] = scrnMsg.dsContrDtlPk_P;
        setArgForSubScreen(prm);
    }
}
