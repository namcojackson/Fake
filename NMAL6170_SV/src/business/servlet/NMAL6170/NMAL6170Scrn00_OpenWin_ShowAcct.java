/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6170.NMAL6170CMsg;
import business.servlet.NMAL6170.constant.NMAL6170Constant;
import business.servlet.NMAL6170.common.NMAL6170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NMAL6170Scrn00_OpenWin_ShowAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_F)) {
            scrnMsg.dsAcctNum_F.setErrorInfo(1, NMAL6170Constant.NMAM8121E, new String[] {scrnMsg.dsAcctNum_F.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_F);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;

        NMAL6170CMsg bizMsg = new NMAL6170CMsg();
        bizMsg.setBusinessID(NMAL6170CommonLogic.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;
        NMAL6170CMsg bizMsg = (NMAL6170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_F);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        Object[] params = new Object[3];
        params[0] = scrnMsg.dsAcctNum_F;
        params[1] = null;

        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
