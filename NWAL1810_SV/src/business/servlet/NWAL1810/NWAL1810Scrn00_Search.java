/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1810;

import static business.servlet.NWAL1810.constant.NWAL1810Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1810.NWAL1810CMsg;
import business.servlet.NWAL1810.common.NWAL1810CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/10/20   Hitachi         H.Watanabe      Create          QC#60258
 *</pre>
 */
public class NWAL1810Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;
        NWAL1810CommonLogic.searchValidCheck(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;

        NWAL1810CMsg bizMsg = new NWAL1810CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;
        NWAL1810CMsg bizMsg = (NWAL1810CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
    }
}
