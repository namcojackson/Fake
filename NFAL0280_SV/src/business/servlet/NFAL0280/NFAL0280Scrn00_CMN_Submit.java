/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0280.NFAL0280CMsg;
import business.servlet.NFAL0280.common.NFAL0280CommonLogic;
import static business.servlet.NFAL0280.constant.NFAL0280Constant.*;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NFAL0280 Service Accrual Reversal Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 *</pre>
 */
public class NFAL0280Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0280BMsg scrnMsg = (NFAL0280BMsg) bMsg;
        NFAL0280CommonLogic.chkPerNm(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_A1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0280BMsg scrnMsg = (NFAL0280BMsg) bMsg;

        NFAL0280CMsg bizMsg = new NFAL0280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0280BMsg scrnMsg = (NFAL0280BMsg) bMsg;
        NFAL0280CMsg bizMsg  = (NFAL0280CMsg) cMsg;
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        NFAL0280CommonLogic.afterSubmitCommonButton(this, bizMsg.getUserID());
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
