/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0590;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0590.NSAL0590CMsg;
import business.servlet.NSAL0590.common.NSAL0590CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         J.Kim           Create          N/A
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC#1264
 * 2016/03/25   Hitachi         M.Gotou         Update          QC#4918
 *</pre>
 */
public class NSAL0590Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
        NSAL0590CommonLogic.addCheckItemForAllHeader(scrnMsg);
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
        NSAL0590CommonLogic.checkAuth(this, scrnMsg);

        // START 2015/12/01 T.Kanasaka [QC#1264, DEL]
//        if (NSAM0006I.equals(bizMsg.getMessageCode())) {
//            setButtonEnabled(INSERTROW[0], false);
//        }
        // END 2015/12/01 T.Kanasaka [QC#1264, DEL]

        // START 2016/03/25 M.Gotou [QC#4918, ADD]
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).dsContrRptGrpDescTxt);
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsContrRptGrpNum_H);
        }
        // END 2016/03/25 M.Gotou [QC#4918, MOD]

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}
