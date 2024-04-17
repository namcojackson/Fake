/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static business.servlet.NSAL0720.common.NSAL0720CommonLogic.*;
import static business.servlet.NSAL0720.constant.NSAL0720Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0720.NSAL0720CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Update Bill To
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#7557
 *</pre>
 */
public class NSAL0720Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        // mod start 2016/05/23 CSA Defect#7557
//        addCheckItemForUpdate(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
        // mod end 2016/05/23 CSA Defect#7557
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;

        NSAL0720CMsg bizMsg = new NSAL0720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        NSAL0720CMsg bizMsg  = (NSAL0720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItemForUpdate(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        initialControlScreen(this, scrnMsg);
        this.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
    }
}
