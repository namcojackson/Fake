/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0460;

import static business.servlet.NSAL0460.common.NSAL0460CommonLogic.initialControlScreen;
import static business.servlet.NSAL0460.constant.NSAL0460Constant.BUSINESS_ID;
import static business.servlet.NSAL0460.constant.NSAL0460Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0460.NSAL0460CMsg;
//import business.servlet.NSAL0460.constant.NSAL0460Constant;

import business.blap.NSAL0460.NSAL0460CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Start Read Capture
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSAL0460Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        scrnMsg.A.setCheckParam(new String[] {NSAL0460Bean.mtrReadDt, NSAL0460Bean.readMtrCnt}, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;

        NSAL0460CMsg bizMsg = new NSAL0460CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        NSAL0460CMsg bizMsg = (NSAL0460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);

    }
}
