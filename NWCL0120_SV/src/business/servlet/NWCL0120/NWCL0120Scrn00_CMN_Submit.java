/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0120;

import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.setAppFracDigit;
import static business.servlet.NWCL0120.common.NWCL0120CommonLogic.setErrDataBackGroundColor;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.BIZ_ID;
import static business.servlet.NWCL0120.constant.NWCL0120Constant.NWZM0634E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0120.NWCL0120CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWCL0120Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NWZM0634E);
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;

        NWCL0120CMsg bizMsg = new NWCL0120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0120BMsg scrnMsg = (NWCL0120BMsg) bMsg;
        NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setAppFracDigit(scrnMsg, getGlobalCompanyCode());
        setErrDataBackGroundColor(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            return;
        }

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
    }
}
