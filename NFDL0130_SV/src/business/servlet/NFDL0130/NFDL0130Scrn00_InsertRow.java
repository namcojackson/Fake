/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0130;

import static business.servlet.NFDL0130.constant.NFDL0130Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0130.NFDL0130CMsg;
import business.servlet.NFDL0130.common.NFDL0130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0130Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0130BMsg scrnMsg = (NFDL0130BMsg) bMsg;

        NFDL0130CMsg bizMsg = new NFDL0130CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0130BMsg scrnMsg = (NFDL0130BMsg) bMsg;
        NFDL0130CMsg bizMsg = (NFDL0130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0130CommonLogic.setupScreenItems(this, scrnMsg);

        NFDL0130CommonLogic.setFocusRule(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).cltWrkItemCd_A);

    }
}
