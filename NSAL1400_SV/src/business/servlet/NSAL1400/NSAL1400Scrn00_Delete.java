/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400;

import static business.servlet.NSAL1400.common.NSAL1400CommonLogic.addCheckDetailItem;
import static business.servlet.NSAL1400.common.NSAL1400CommonLogic.controlField;
import static business.servlet.NSAL1400.constant.NSAL1400Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1400.NSAL1400CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400Scrn00_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;

        NSAL1400CMsg bizMsg = new NSAL1400CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        NSAL1400CMsg bizMsg  = (NSAL1400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        controlField(this, scrnMsg);
        addCheckDetailItem(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).svcLineBizCd_A);
    }
}
