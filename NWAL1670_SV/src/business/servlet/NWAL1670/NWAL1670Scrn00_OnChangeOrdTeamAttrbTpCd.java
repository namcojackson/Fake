/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1670;

import static business.servlet.NWAL1670.constant.NWAL1670Constant.BIZ_ID;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1670.NWAL1670CMsg;
import business.servlet.NWAL1670.common.NWAL1670CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NWAL1670Scrn00_OnChangeOrdTeamAttrbTpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1670BMsg scrnMsg = (NWAL1670BMsg) bMsg;

        NWAL1670CMsg bizMsg = new NWAL1670CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1670BMsg scrnMsg = (NWAL1670BMsg) bMsg;
        NWAL1670CMsg bizMsg = (NWAL1670CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1670CommonLogic.controlScreenFieldsChgAttrb(this, scrnMsg);

        if (scrnMsg.C.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.C.no(0).ordTeamAttrbValTxt_C);
        } else {
            scrnMsg.setFocusItem(scrnMsg.ordTeamAttrbTpCd);
        }
    }
}
