/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0380.NSAL0380CMsg;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Hitachi         O.Okuma         Create          QC3029
 * 2016/03/14   Hitachi         O.Okuma         Update          QC4900
 *</pre>
 */
public class NSAL0380Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

         NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
         addCheckItem(scrnMsg);
         scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

         NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

         NSAL0380CMsg bizMsg = new NSAL0380CMsg();
         bizMsg.setBusinessID(NSAL0380Constant.BUSINESS_ID);
         bizMsg.setFunctionCode(NSAL0380Constant.FUNCTION_SEARCH);
         EZDMsg.copy(scrnMsg, null, bizMsg, null);

         return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
         NSAL0380CMsg bizMsg = (NSAL0380CMsg) cMsg;

         EZDMsg.copy(bizMsg, null, scrnMsg, null);

         if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
             throw new EZDFieldErrorException();
         }
         NSAL0380CommonLogic.controlDtl(this, scrnMsg);
         setButtonEnabled(NSAL0380Constant.BTN_CMN_SUBMIT_BTN_NM, true);
         scrnMsg.putErrorScreen();
    }

    private void addCheckItem(NSAL0380BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.serNum_HF);
        scrnMsg.addCheckItem(scrnMsg.serNum_HT);
        scrnMsg.addCheckItem(scrnMsg.svcMachMstrPk_HF);
        scrnMsg.addCheckItem(scrnMsg.svcMachMstrPk_HT);
    }
}
