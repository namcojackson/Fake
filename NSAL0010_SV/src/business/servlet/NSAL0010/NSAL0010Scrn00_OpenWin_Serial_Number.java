/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_Serial_Number extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        scrnMsg.xxPopPrm_00.clear();
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
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL0010Constant.MODE_ALL_SERIALIZED_ITEMS);

        Object[] params = new Object[17];
        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.serNum_H1;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;
        params[8] = scrnMsg.xxPopPrm_08;
        params[9] = scrnMsg.xxPopPrm_09;
        params[10] = scrnMsg.xxPopPrm_10;
        params[11] = scrnMsg.mdseCd_H1;
        params[12] = scrnMsg.svcMachMstrPk_H1;
        params[13] = scrnMsg.xxPopPrm_13;
        params[14] = scrnMsg.serNum_H1;
        params[15] = scrnMsg.mdseCd_H1;
        params[16] = scrnMsg.xxPopPrm_16;

        setArgForSubScreen(params);
    }
}
