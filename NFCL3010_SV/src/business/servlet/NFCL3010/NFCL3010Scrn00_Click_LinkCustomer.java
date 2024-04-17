/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Hitachi         T.Mizuki        Create          N/A
 * 2018/02/02   Fujitsu         T.Murai         Update          S21_NA#21372
 *</pre>
 */
public class NFCL3010Scrn00_Click_LinkCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

        // scrnMsg.payerCustCd_H.clear(); // Del 2018/02/02 S21_NA#21372
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();

        // NMAL6760
        Object[] param = new Object[15];
        // Mod Start 2018/02/02 S21_NA#21372
        param[0] = scrnMsg.dsAcctNum_H;
        // param[0] = scrnMsg.payerCustCd_H;
        // Mod End 2018/02/02 S21_NA#21372
        param[1] = scrnMsg.dsAcctNm_H;
        param[2] = scrnMsg.xxPopPrm_01;
        param[3] = scrnMsg.xxPopPrm_02;
        param[4] = scrnMsg.xxPopPrm_03;
        param[5] = scrnMsg.xxPopPrm_04;
        param[6] = scrnMsg.xxPopPrm_05;
        param[7] = scrnMsg.xxPopPrm_06;
        param[8] = scrnMsg.xxPopPrm_07;
        param[9] = scrnMsg.xxPopPrm_08;
        param[10] = scrnMsg.xxPopPrm_09;
        param[11] = scrnMsg.xxPopPrm_10;
        param[12] = scrnMsg.xxPopPrm_11;
        param[13] = scrnMsg.xxPopPrm_12;
        param[14] = scrnMsg.xxPopPrm_13;

        setArgForSubScreen(param);

    }
}
