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

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NMAL6700Scrn00_AddCertificationReq extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CommonLogic.clearMandatoryError(scrnMsg);
        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.N.no(i).svcAccsPmitNum_N1);
            scrnMsg.addCheckItem(scrnMsg.N.no(i).svcAccsPmitDescTxt_N1);
            scrnMsg.addCheckItem(scrnMsg.N.no(i).effFromDt_N1);
            scrnMsg.addCheckItem(scrnMsg.N.no(i).effToDt_N1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID("NMAL6700");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg  = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6700CommonLogic.checkAuth(scrnMsg, this);
        NMAL6700CommonLogic.setBgColorForCertificationReq(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.dsAcctDunsNum_M1);
        if (scrnMsg.N.getValidCount() > 0) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                scrnMsg.setFocusItem(scrnMsg.N.no(0).svcAccsPmitNum_N1);
            } else {
                scrnMsg.setFocusItem(scrnMsg.N.no(scrnMsg.N.getValidCount() - 1).svcAccsPmitNum_N1);
                scrnMsg.N.no(scrnMsg.N.getValidCount() - 1).svcAccsPmitDescTxt_N1.setInputProtected(true);
            }
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsAcctDunsNum_M1);
        }

        NMAL6700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
    }
}
