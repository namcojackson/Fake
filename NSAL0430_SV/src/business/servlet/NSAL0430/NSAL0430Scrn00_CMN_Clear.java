/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0430;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0430.NSAL0430CMsg;
import business.servlet.NSAL0430.common.NSAL0430CommonLogic;
import business.servlet.NSAL0430.constant.NSAL0430Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0430Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0430BMsg scrnMsg = (NSAL0430BMsg) bMsg;
        NSAL0430CMsg bizMsg = new NSAL0430CMsg();
        bizMsg.setBusinessID(NSAL0430Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0430Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0430BMsg scrnMsg = (NSAL0430BMsg) bMsg;
        NSAL0430CMsg bizMsg = (NSAL0430CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0430CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0430CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
