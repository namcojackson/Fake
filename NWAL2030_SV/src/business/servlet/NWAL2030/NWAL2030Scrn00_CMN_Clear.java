/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2030;

import static business.servlet.NWAL2030.constant.NWAL2030Constant.BUSINESS_ID;
import static business.servlet.NWAL2030.constant.NWAL2030Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2030.NWAL2030CMsg;
import business.servlet.NWAL2030.common.NWAL2030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2030Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL2030Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;
        NWAL2030CMsg bizMsg = new NWAL2030CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;
        NWAL2030CMsg bizMsg = (NWAL2030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2030CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.hldRsnCd);
    }
}
