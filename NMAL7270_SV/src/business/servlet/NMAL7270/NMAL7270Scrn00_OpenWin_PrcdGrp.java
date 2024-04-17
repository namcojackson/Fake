/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_0;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/28   Fujitsu         Y.Kanefusa      Create          S21_NA#9694
 *</pre>
 */
public class NMAL7270Scrn00_OpenWin_PrcdGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        Object[] params = new Object[IDX_1];
        params[IDX_0] = scrnMsg.prcRulePrcdPk_H1;
        setArgForSubScreen(params);
        openMultiModeScreen();

    }
}
