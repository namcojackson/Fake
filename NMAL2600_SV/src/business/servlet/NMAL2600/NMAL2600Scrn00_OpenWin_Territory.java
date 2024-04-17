/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2600.NMAL2600CMsg;
import business.servlet.NMAL2600.common.NMAL2600CommonLogic;
import business.servlet.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2600Scrn00_OpenWin_Territory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        if (!NMAL2600CommonLogic.chkTerritoryCodeforParam(scrnMsg)) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        NMAL2600CMsg bizMsg = new NMAL2600CMsg();
        bizMsg.setBusinessID(NMAL2600Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2600Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;
        NMAL2600CMsg bizMsg = (NMAL2600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NMAL2600Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        Object[] params = NMAL2600CommonLogic.setParamsForOtherScreen(scrnMsg);
        setArgForSubScreen(params);

    }
}
