/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0330;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0330.NSAL0330CMsg;
import business.servlet.NSAL0330.common.NSAL0330CommonLogic;
import business.servlet.NSAL0330.constant.NSAL0330Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Hitachi         O.Okuma         Create          QC#12430
 *</pre>
 */
public class NSAL0330Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;

        NSAL0330CMsg bizMsg = new NSAL0330CMsg();
        bizMsg.setBusinessID(NSAL0330Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0330BMsg scrnMsg = (NSAL0330BMsg) bMsg;
        NSAL0330CMsg bizMsg = (NSAL0330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0330CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.baseBllgTmgCd_H1);
    }
}
