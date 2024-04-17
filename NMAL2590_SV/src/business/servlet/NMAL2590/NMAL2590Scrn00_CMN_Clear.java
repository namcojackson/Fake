/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2590;

import static business.servlet.NMAL2590.constant.NMAL2590Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2590.common.NMAL2590CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2590Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/25   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 *</pre>
 */
public class NMAL2590Scrn00_CMN_Clear extends S21CommonHandler {

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
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.clear();

        NMAL2590CommonLogic.initCmnBtnProp(this);
        scrnMsg.setFocusItem(scrnMsg.ctyAddr);
    }
}
