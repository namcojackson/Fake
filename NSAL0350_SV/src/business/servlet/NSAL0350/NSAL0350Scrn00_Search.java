/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0350;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0350.NSAL0350CMsg;
import business.servlet.NSAL0350.common.NSAL0350CommonLogic;
import business.servlet.NSAL0350.constant.NSAL0350Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Schedules Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         K.Kitachi       Create          QC#20048
 * </pre>
 */
public class NSAL0350Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;
        NSAL0350CommonLogic.inputCheck(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;
        NSAL0350CMsg bizMsg = new NSAL0350CMsg();
        bizMsg.setBusinessID(NSAL0350Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0350BMsg scrnMsg = (NSAL0350BMsg) bMsg;
        NSAL0350CMsg bizMsg = (NSAL0350CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0350CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
    }
}
