/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import static business.servlet.NSAL0560.common.NSAL0560CommonLogic.*;
import static business.servlet.NSAL0560.constant.NSAL0560Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0560.NSAL0560CMsg;
import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2016/11/22   Hitachi         T.Tomita        Update          QC#15942
 *</pre>
 */
public class NSAL0560_NSAL0330 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;

        NSAL0560CMsg bizMsg = new NSAL0560CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg = (NSAL0560CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0560CommonLogic.copyAMsgToBMsg(scrnMsg);

        initialControlScreen(this, scrnMsg);
    }
}
