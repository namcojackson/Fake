/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import static business.servlet.NSAL0570.common.NSAL0570CommonLogic.copyAMsgToBMsg;
import static business.servlet.NSAL0570.common.NSAL0570CommonLogic.initialControlScreen;
import static business.servlet.NSAL0570.constant.NSAL0570Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0570.NSAL0570CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Hitachi         O.Okuma         Create          QC#12430
 *</pre>
 */
public class NSAL0570Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CMsg bizMsg = (NSAL0570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        copyAMsgToBMsg(scrnMsg);

        initialControlScreen(this, scrnMsg);    }
}
