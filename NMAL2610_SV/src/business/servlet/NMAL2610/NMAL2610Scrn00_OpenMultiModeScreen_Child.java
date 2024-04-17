/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import static business.servlet.NMAL2610.constant.NMAL2610Constant.TAB_NAME;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 *</pre>
 */
public class NMAL2610Scrn00_OpenMultiModeScreen_Child extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
        }
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add Start --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        Object[] params = new Object[NMAL2610Constant.POP_PAR_1];

        int index = getButtonSelectNumber();
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(index).orgCd_B1)) {
            params[NMAL2610Constant.POP_PAR_0] = scrnMsg.B.no(index).orgCd_B1;
        }

        setArgForSubScreen(params);
        openMultiModeScreen(TAB_NAME);

    }
}
