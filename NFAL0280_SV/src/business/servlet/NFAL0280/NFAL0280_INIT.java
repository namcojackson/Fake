/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0280;

import static business.servlet.NFAL0280.constant.NFAL0280Constant.BUSINESS_APPL_ID;
import static business.servlet.NFAL0280.constant.NFAL0280Constant.FUNC_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0280.NFAL0280CMsg;
import business.servlet.NFAL0280.constant.NFAL0280Constant;
import business.servlet.NFAL0280.common.NFAL0280CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
/**
 * <pre>
 * NFAL0280 Service Accrual Reversal Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 *</pre>
 */
public class NFAL0280_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NFAL0280Constant.BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0280BMsg scrnMsg = (NFAL0280BMsg) bMsg;

        NFAL0280CMsg bizMsg = new NFAL0280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0280BMsg scrnMsg = (NFAL0280BMsg) bMsg;
        NFAL0280CMsg bizMsg  = (NFAL0280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFAL0280CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL0280BMsg scrnMsg = (NFAL0280BMsg) bMsg;

        scrnMsg.glPerNm_A1.setNameForMessage("Target Month");
        scrnMsg.xxDtNm_A1.setNameForMessage("Target Data to be reversed");
    }
}
