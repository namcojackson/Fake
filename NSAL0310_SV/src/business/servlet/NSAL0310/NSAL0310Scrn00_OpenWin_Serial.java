/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2016/11/22   Hitachi         K.Kojima        Update          QC#16168
 *</pre>
 */
public class NSAL0310Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        // START 2016/11/22 K.Kojima [QC#16168,DEL]
        // NSAL0310CommonLogic.clearPopupParameter(scrnMsg);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00,
        // NSAL0310Constant.NSAL0030_MODE_ALL_SERIALIZED_ITEMS);
        // Object[] params = new Object[17];
        // params[0] = scrnMsg.xxPopPrm_00;
        // params[1] = scrnMsg.serNum;
        // params[2] = scrnMsg.xxPopPrm_02;
        // params[3] = scrnMsg.xxPopPrm_03;
        // params[4] = scrnMsg.xxPopPrm_04;
        // params[5] = scrnMsg.xxPopPrm_05;
        // params[6] = scrnMsg.xxPopPrm_06;
        // params[7] = scrnMsg.xxPopPrm_07;
        // params[8] = scrnMsg.xxPopPrm_08;
        // params[9] = scrnMsg.xxPopPrm_09;
        // params[10] = scrnMsg.xxPopPrm_10;
        // params[11] = scrnMsg.xxPopPrm_11;
        // params[12] = scrnMsg.svcMachMstrPk;
        // params[13] = scrnMsg.xxPopPrm_13;
        // params[14] = scrnMsg.serNum;
        // params[15] = scrnMsg.xxPopPrm_15;
        // params[16] = scrnMsg.xxPopPrm_16;
        // END 2016/11/22 K.Kojima [QC#16168,DEL]

        // START 2016/11/22 K.Kojima [QC#16168,ADD]
        ZYPTableUtil.clear(scrnMsg.O);
        Object[] params = new Object[31];
        params[2] = scrnMsg.serNum;
        params[30] = scrnMsg.O;
        // END 2016/11/22 K.Kojima [QC#16168,ADD]

        setArgForSubScreen(params);
    }
}
