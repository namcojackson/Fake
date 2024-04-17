/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1390;

import static business.servlet.NSAL1390.constant.NSAL1390Constant.BUSINESS_ID;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.FUNCTION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1390.NSAL1390CMsg;
import business.servlet.NSAL1390.common.NSAL1390CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 *</pre>
 */
public class NSAL1390Scrn00_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1390BMsg scrnMsg = (NSAL1390BMsg) bMsg;

        NSAL1390CMsg bizMsg = new NSAL1390CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1390BMsg scrnMsg = (NSAL1390BMsg) bMsg;
        NSAL1390CMsg bizMsg = (NSAL1390CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1390CommonLogic.controlField(this, scrnMsg);
        NSAL1390CommonLogic.addCheckDetailItem(scrnMsg);
    }
}
