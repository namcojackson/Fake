/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static business.servlet.NSAL0720.common.NSAL0720CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0720.NSAL0720CMsg;
import business.servlet.NSAL0720.constant.NSAL0720Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/08   Hitachi         T.Tsuchida      Update          QC#1608
 *</pre>
 */
public class NSAL0720Scrn00_ApplyToAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        addCheckItemForApply(scrnMsg);

        if (!hasValue(scrnMsg.billToCustCd_H1)) {
            scrnMsg.billToCustCd_H1.setErrorInfo(1, NSAL0720Constant.NSAM0007E, new String[]{"New Bill To"});
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;

        NSAL0720CMsg bizMsg = new NSAL0720CMsg();
        bizMsg.setBusinessID("NSAL0720");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        NSAL0720CMsg bizMsg  = (NSAL0720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H1);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
    }
}
