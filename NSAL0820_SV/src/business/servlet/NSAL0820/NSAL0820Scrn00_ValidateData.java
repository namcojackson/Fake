/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820;

import static business.servlet.NSAL0820.common.NSAL0820CommonLogic.checkInputForTable;
import static business.servlet.NSAL0820.common.NSAL0820CommonLogic.initialControlScreen;
import static business.servlet.NSAL0820.common.NSAL0820CommonLogic.setErrorInfo;

import static business.servlet.NSAL0820.constant.NSAL0820Constant.BUSINESS_ID;
import static business.servlet.NSAL0820.constant.NSAL0820Constant.FUNCTION_SUBMIT;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0820.NSAL0820CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto         Create          N/A
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0820Scrn00_ValidateData extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;
        checkInputForTable(this, scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;

        NSAL0820CMsg bizMsg = new NSAL0820CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;
        NSAL0820CMsg bizMsg = (NSAL0820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setErrorInfo(this, scrnMsg);

        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        checkInputForTable(this, scrnMsg);
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
