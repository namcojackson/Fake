/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.common.NSAL0490CommonLogic;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/24   CITS            S.Endo          Create          N/A
 *</pre>
 */
public class NSAL0490Scrn00_TAB_Criticality extends S21CommonHandler {

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

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        scrnMsg.clearAllGUIAttribute(NSAL0490Constant.SCREEN_ID_00);

        NSAL0490CommonLogic.controlHdrField(this, scrnMsg, false);
        NSAL0490CommonLogic.controlCriticalityField(this, scrnMsg, true);
        NSAL0490CommonLogic.setTblBackColorCriticality(scrnMsg);
        NSAL0490CommonLogic.controlCommonTabBtn(scrnMsg, true, true, true, true, false);

        scrnMsg.xxDplyTab.setValue(NSAL0490Constant.TAB_CRITICALITY);
    }
}
