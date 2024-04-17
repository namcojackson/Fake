/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2840;

import static business.servlet.NMAL2840.constant.NMAL2840Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2840.NMAL2840CMsg;
import business.servlet.NMAL2840.common.NMAL2840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Fujitsu         R.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2840Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;

        NMAL2840CMsg bizMsg = new NMAL2840CMsg();
        bizMsg.setBusinessID("NMAL2840");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;
        NMAL2840CMsg bizMsg = (NMAL2840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2840CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dunsCritCd_PS);
    }
}
