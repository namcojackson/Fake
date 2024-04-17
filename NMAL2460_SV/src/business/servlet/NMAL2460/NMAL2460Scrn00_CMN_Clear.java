/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.EZD_FUNC_CD_INQ;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        NMAL2460CMsg bizMsg = new NMAL2460CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        NMAL2460CMsg bizMsg = (NMAL2460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2460CommonLogic.controlScreen(this, scrnMsg);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
    }
}
