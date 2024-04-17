/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0590;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0590.NSAL0590CMsg;
import business.servlet.NSAL0590.common.NSAL0590CommonLogic; // import
// business.servlet.NSAL0590.constant.NSAL0590Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0590Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrRptGrpDescTxt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrRptGrpStartDt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrRptGrpEndDt);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;

        NSAL0590CMsg bizMsg = new NSAL0590CMsg();
        bizMsg.setBusinessID("NSAL0590");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
        NSAL0590CMsg bizMsg = (NSAL0590CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0590CommonLogic.initialize(this, scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).dsContrRptGrpDescTxt);
            } else {
                scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).dsContrRptGrpStartDt.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).dsContrRptGrpDescTxt);
            }
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpDescTxt_H);
        }
    }
}
