/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NMAL6700.NMAL6700CMsg;
// import business.servlet.NMAL6700.constant.NMAL6700Constant;

import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6700Scrn00_ShowContact extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        NMAL6700_DBMsg dbMsg = scrnMsg.D.no(selectIdx);

        Object[] params = new Object[4];
        params[0] = dbMsg.ctacPsnNum_D1;
        params[1] = scrnMsg.dsAcctNum_H1;
        params[2] = null;
        params[3] = null;

        NMAL6700CommonLogic.setEventParam(scrnMsg, NMAL6700Constant.SHOW_CONTACT_EVENT);

        setArgForSubScreen(params);
    }
}
