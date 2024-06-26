/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1030;

import static business.servlet.NSAL1030.constant.NSAL1030Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1030.NSAL1030CMsg;
import business.servlet.NSAL1030.common.NSAL1030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1030_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1030BMsg scrnMsg = (NSAL1030BMsg) bMsg;
        NSAL1030CMsg bizMsg = new NSAL1030CMsg();

        Serializable arg = getArgForSubScreen();
        NSAL1030CommonLogic.setInputParam(scrnMsg, arg);

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1030BMsg scrnMsg = (NSAL1030BMsg) bMsg;
        NSAL1030CMsg bizMsg = (NSAL1030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1030CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }
}
