/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0690;

import static business.servlet.NSAL0690.common.NSAL0690CommonLogic.*;
import static business.servlet.NSAL0690.constant.NSAL0690Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0690.NSAL0690CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0690Scrn00_ApplyToAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        addCheckItemForApply(scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxNum_H1)) {
            scrnMsg.xxNum_H1.setErrorInfo(1, NSAM0007E, new String[]{"Duration"});
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgCycleUomCd_H3)) {
            scrnMsg.bllgCycleUomCd_H3.setErrorInfo(1, NSAM0007E, new String[]{"Period"});
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;

        NSAL0690CMsg bizMsg = new NSAL0690CMsg();
        bizMsg.setBusinessID("NSAL0690");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        NSAL0690CMsg bizMsg  = (NSAL0690CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
    }
}
