/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0520.NSAL0520CMsg;
import business.servlet.NSAL0520.common.NSAL0520CommonLogic;
import business.servlet.NSAL0520.constant.NSAL0520Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0520Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0520BMsg scrnMsg = (NSAL0520BMsg) bMsg;
        NSAL0520CMsg bizMsg = new NSAL0520CMsg();
        bizMsg.setBusinessID(NSAL0520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0520Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0520BMsg scrnMsg = (NSAL0520BMsg) bMsg;
        NSAL0520CMsg bizMsg = (NSAL0520CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0520CommonLogic.setupScreenItems(this, scrnMsg);
    }
}
