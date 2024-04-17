/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1020;

import static business.servlet.NSAL1020.constant.NSAL1020Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1020.NSAL1020CMsg;
import business.servlet.NSAL1020.common.NSAL1020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1020BMsg scrnMsg = (NSAL1020BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        NSAL1020CommonLogic.setInputParam(scrnMsg, arg);

        NSAL1020CMsg bizMsg = new NSAL1020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1020BMsg scrnMsg = (NSAL1020BMsg) bMsg;
        NSAL1020CMsg bizMsg = (NSAL1020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1020CommonLogic.initialize(this);
        NSAL1020CommonLogic.screenItemControl(this, scrnMsg, bizMsg);
        scrnMsg.setFocusItem(scrnMsg.serNum_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1020BMsg scrnMsg = (NSAL1020BMsg) bMsg;
        scrnMsg.serNum_H.setNameForMessage("Serial#");
    }
}
