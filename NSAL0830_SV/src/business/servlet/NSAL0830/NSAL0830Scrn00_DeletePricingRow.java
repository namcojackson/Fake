/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.checkInputForTable;
import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.initialControlScreen;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0830.NSAL0830CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/19   Hitachi         T.Iwamoto       Update          QC#6691
 *</pre>
 */
public class NSAL0830Scrn00_DeletePricingRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        checkInputForTable(this, scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;

        NSAL0830CMsg bizMsg = new NSAL0830CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        // MOD start 2016/04/19 CSA Defect#6691
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        // MOD end 2016/04/19 CSA Defect#6691
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        NSAL0830CMsg bizMsg = (NSAL0830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
    }
}