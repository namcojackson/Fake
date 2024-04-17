/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import static business.servlet.NWAL2300.constant.NWAL2300Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2300.NWAL2300CMsg;
import business.servlet.NWAL2300.common.NWAL2300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL2300Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;

        NWAL2300CMsg bizMsg = new NWAL2300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;
        NWAL2300CMsg bizMsg = (NWAL2300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2300CommonLogic.initCmnBtnProp(this);
        NWAL2300CommonLogic.initBtnProp(this, scrnMsg);
        NWAL2300CommonLogic.setProtect(this, scrnMsg);
        NWAL2300CommonLogic.setControlFieldsForDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H1);
    }
}
