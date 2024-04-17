/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         N.Sugiura       Create          CSA
 *</pre>
 */
public class NMAL6750Scrn00_GetAccountName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
            scrnMsg.dsAcctNum_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.dsAcctNum_H1.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);

    }
}
