/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310;

import static business.servlet.NSAL1310.constant.NSAL1310Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1310.NSAL1310CMsg;
import business.servlet.NSAL1310.common.NSAL1310CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/07   Hitachi         K.Kojima        Update          QC#15136
 *</pre>
 */
public class NSAL1310Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        NSAL1310CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        NSAL1310CMsg bizMsg = new NSAL1310CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        NSAL1310CMsg bizMsg = (NSAL1310CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1310CommonLogic.setupScreenItemsList(this, scrnMsg);
        NSAL1310CommonLogic.addCheckItemCheck(scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.A.no(0).svcTermAttrbDefValTxt_A1);
    }
}
