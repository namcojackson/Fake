/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0020.common.NSAL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2013/09/10   Hitachi        T.Yonekura         Update          QC1922
 * 2015/10/19   Hitachi        Y.Tsuchimoto       Update          N/A(No Mark up comment)
 *</pre>
 */
public class NSAL0020Scrn00_OpenWin_Contract_Number extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

        NSAL0020CommonLogic.clearPopupParam(scrnMsg);

        Object[] params = new Object[13];
//
//        params[0] = scrnMsg.dsContrNum;
//        params[1] = scrnMsg.xxPopPrm_01;
//        params[2] = scrnMsg.xxPopPrm_02;
//        params[3] = scrnMsg.xxPopPrm_03;
//        params[4] = scrnMsg.xxPopPrm_04;
//        params[5] = scrnMsg.xxPopPrm_05;
//        params[6] = scrnMsg.xxPopPrm_06;
//        params[7] = scrnMsg.xxPopPrm_07;
//        params[8] = scrnMsg.xxPopPrm_08;
//        params[9] = scrnMsg.xxPopPrm_09;
//        params[10] = scrnMsg.xxPopPrm_10;
//        params[11] = scrnMsg.dsContrPk;
//        params[12] = scrnMsg.dsContrDtlPk;

        setArgForSubScreen(params);

    }
}
