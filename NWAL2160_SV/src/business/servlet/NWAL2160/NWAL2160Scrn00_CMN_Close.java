/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2160;

import static business.servlet.NWAL2160.constant.NWAL2160Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2160.NWAL2160CMsg;
import business.servlet.NWAL2160.common.NWAL2160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2160Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2160Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;
        NWAL2160CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;

        NWAL2160CMsg bizMsg = new NWAL2160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;
        NWAL2160CMsg bizMsg = (NWAL2160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2160CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }
        Object[] arg = (Object[]) getArgForSubScreen();
        NWAL2160CommonLogic.setOutputPrm(scrnMsg, arg);
    }
}
