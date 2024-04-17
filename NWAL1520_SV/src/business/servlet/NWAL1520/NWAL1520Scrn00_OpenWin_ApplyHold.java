/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1520.common.NWAL1520CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1520Scrn00_OpenWin_ApplyHold
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520Scrn00_OpenWin_ApplyHold extends S21CommonHandler {

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

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        scrnMsg.xxScrEventNm.clear();

        String submitLinkNm = getSubmitedFieldNm(ctx);
        scrnMsg.xxScrEventNm.setValue(submitLinkNm);
        Object[] params = NWAL1520CommonLogic.getApplyHoldPopUpParams(scrnMsg, getGlobalCompanyCode());

        setArgForSubScreen(params);

    }
}
