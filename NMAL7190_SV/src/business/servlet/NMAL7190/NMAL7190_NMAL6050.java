/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_BTN_FLG_FROM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7190_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcGrpFromTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcGrpThruTxt_A1);
        }
    }
}
