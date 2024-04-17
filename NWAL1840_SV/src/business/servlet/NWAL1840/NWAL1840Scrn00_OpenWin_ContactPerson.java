/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import business.blap.NWAL1840.NWAL1840CMsg;
// import business.servlet.NWAL1840.constant.NWAL1840Constant;

import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         T.murai         Create          N/A
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_ContactPerson extends S21CommonHandler {

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

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        Object[] params = NWAL1840CommonLogic.getParamNMAL6770(scrnMsg);
        setArgForSubScreen(params);

    }
}
