/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1840_NMAL6050 extends S21CommonHandler {

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

        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscDescTxt);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.pmtTermCashDiscDescTxt);

        NWAL1840CommonLogic.setProtect(this, scrnMsg);
    }
}