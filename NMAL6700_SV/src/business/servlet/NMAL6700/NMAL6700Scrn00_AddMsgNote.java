/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/11/24   Hitachi         Y.Tsuchimoto    Update          N/A
 *</pre>
 */
public class NMAL6700Scrn00_AddMsgNote extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CommonLogic.clearMandatoryError(scrnMsg);
        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.J.no(i).dsCustMsgTxt_J1);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).effThruDt_J1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID(NMAL6700Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6700CommonLogic.checkAuthForMsgNote(scrnMsg, this);
        NMAL6700CommonLogic.setBgColorForMsgNote(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.dsAcctDunsNum_M1);
        if (scrnMsg.J.getValidCount() > 0) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                scrnMsg.setFocusItem(scrnMsg.J.no(0).lineBizTpCd_J3);
            } else {
                scrnMsg.setFocusItem(scrnMsg.J.no(scrnMsg.J.getValidCount() - 1).lineBizTpCd_J3);
            }
        }
        NMAL6700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
    }
}