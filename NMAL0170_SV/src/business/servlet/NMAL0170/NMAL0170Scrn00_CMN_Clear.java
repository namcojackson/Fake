/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_INIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0170.NMAL0170CMsg;
import business.servlet.NMAL0170.common.NMAL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0170Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima          Create          N/A
 *</pre>
 */
public class NMAL0170Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - Clear All.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        NMAL0170CommonLogic.clearRowsBG(scrnMsg, scrnMsg.A, "A");
    }

    @Override
    /**
     * Set Request Date Event
     * - Recreate Pull down List.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    /**
     * Do Process Event
     * - Set Protect for input field.
     * - Set Focus Item - Item Number.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg  = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0170CommonLogic.initCmnBtnProp(this);
        NMAL0170CommonLogic.setCmnBtnProp(this, scrnMsg, EVENT_INIT);

        //Focus : Item Number
        scrnMsg.setFocusItem(scrnMsg.supdToMdseCd);
    }
}
