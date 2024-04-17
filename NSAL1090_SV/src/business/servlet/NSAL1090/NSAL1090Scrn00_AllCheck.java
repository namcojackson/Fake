/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1090.NSAL1090CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1090Scrn00_AllCheck extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = new NSAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = (NSAL1090CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
