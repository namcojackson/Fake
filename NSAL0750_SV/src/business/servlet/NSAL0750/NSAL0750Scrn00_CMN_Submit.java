/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0750;

import static business.servlet.NSAL0750.constant.NSAL0750Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0750.NSAL0750CMsg;
import business.servlet.NSAL0750.common.NSAL0750CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0750Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0750BMsg scrnMsg = (NSAL0750BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.baseBllgTmgCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0750BMsg scrnMsg = (NSAL0750BMsg) bMsg;

        NSAL0750CMsg bizMsg = new NSAL0750CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0750BMsg scrnMsg = (NSAL0750BMsg) bMsg;
        NSAL0750CMsg bizMsg = (NSAL0750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        } else {
            NSAL0750CommonLogic.initInactiveCommonButton(this, scrnMsg);
        }
    }
}
