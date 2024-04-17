/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.MSG_PARAM_ORD_NUM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CITS            N.Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_AppvlHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.poNum)) {
            scrnMsg.poNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ORD_NUM });
            scrnMsg.addCheckItem(scrnMsg.poNum);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        Object[] params = new Object[2];
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, APVL_HIST_SRC_TP.PO_ENTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.poNum);
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        setArgForSubScreen(params);
    }
}
