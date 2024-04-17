/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.checkInputForTable;
import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.initialControlScreen;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.BUSINESS_ID;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.FUNCTION_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0810.NSAL0810CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0810Scrn00_CMN_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        checkInputForTable(this, scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        NSAL0810CMsg bizMsg = new NSAL0810CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CMsg bizMsg = (NSAL0810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
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
