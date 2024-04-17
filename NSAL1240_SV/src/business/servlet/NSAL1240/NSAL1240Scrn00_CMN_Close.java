/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1240;

import static business.servlet.NSAL1240.constant.NSAL1240Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1240.NSAL1240CMsg;
import business.servlet.NSAL1240.common.NSAL1240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1240Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NSAL1240BMsg scrnMsg = (NSAL1240BMsg) bMsg;
        NSAL1240CMsg bizMsg = new NSAL1240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1240BMsg scrnMsg = (NSAL1240BMsg) bMsg;
        NSAL1240CMsg bizMsg = (NSAL1240CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
        }
        scrnMsg.putErrorScreen();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = NSAL1240CommonLogic.setOutputParam(scrnMsg, arg);
            setArgForSubScreen(params);
        }
    }
}
