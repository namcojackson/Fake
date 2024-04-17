/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0190;

import static business.servlet.NMAL0190.constant.NMAL0190Constant.BIZ_ID;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0190.NMAL0190CMsg;
import business.servlet.NMAL0190.common.NMAL0190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0190Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/03/02   CITS            S.Tanikawa      UPDATE          QC#2669
 * 
 *</pre>
 */
public class NMAL0190Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - do nothing
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    /**
     * Set Request Date Event
     * - Reset Table From BLAP
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        NMAL0190CMsg bizMsg = new NMAL0190CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    /**
     * Do Process Event
     * - Set Protect Table
     * - Set Focus Item - Forward.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0190BMsg scrnMsg = (NMAL0190BMsg) bMsg;
        NMAL0190CMsg bizMsg = (NMAL0190CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0190CommonLogic.initCmnBtnProp(this);
        NMAL0190CommonLogic.setScrnLineProtected(scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // UPDATE START 2016/03/02 QC#2669
        scrnMsg.setFocusItem(scrnMsg.A.no(0).mdseItemStsCd_SL);
        // UPDATE END 2016/03/02 QC#2669
    }
}
