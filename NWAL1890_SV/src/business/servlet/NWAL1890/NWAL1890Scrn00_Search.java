/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1890;

import static business.servlet.NWAL1890.constant.NWAL1890Constant.BIZ_ID;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.MESSAGE_KIND_ERROR;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1890.NWAL1890CMsg;
import business.servlet.NWAL1890.common.NWAL1890CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/09   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        NWAL1890CommonLogic.addChkItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        NWAL1890CMsg bizMsg = new NWAL1890CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;
        NWAL1890CMsg bizMsg = (NWAL1890CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1890CommonLogic.addChkItem(scrnMsg);

        if (scrnMsg.getMessageType() == 1 || MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        Serializable arg = getArgForSubScreen();
        NWAL1890CommonLogic.setOutputParameter(scrnMsg, arg);

    }
}
