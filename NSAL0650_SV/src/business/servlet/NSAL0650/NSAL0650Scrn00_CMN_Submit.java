/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0650;

import static business.servlet.NSAL0650.common.NSAL0650CommonLogic.addCheckItem;
import static business.servlet.NSAL0650.common.NSAL0650CommonLogic.initialControlScreen;
import static business.servlet.NSAL0650.constant.NSAL0650Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0650.NSAL0650CMsg;
import business.servlet.NSAL0650.constant.NSAL0650Constant.BTN_LBL;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2016/12/06   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0650Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;

        NSAL0650CMsg bizMsg = new NSAL0650CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;
        NSAL0650CMsg bizMsg  = (NSAL0650CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItem(scrnMsg);
        // mod start 2016/12/06 CSA QC#4210
        initialControlScreen(this, scrnMsg);
        // mod end 2016/12/06 CSA QC#4210
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        if ("W".equals(bizMsg.getMessageKind())) {
            return;
        }

        this.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
    }
}
